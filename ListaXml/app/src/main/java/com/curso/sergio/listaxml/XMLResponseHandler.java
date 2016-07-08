package com.curso.sergio.listaxml;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


class XMLResponseHandler implements ResponseHandler<List<String>> {

	private static final String NOMBRE_TAG = "name";
	private static final String HORA_TAG = "cmt";
	private String mHora, mNombre;
	private boolean mIsParsingHora, mIsParsingNombre;
	private boolean tieneHora = false;
	private final List<String> mResults = new ArrayList<String>();

	//Método que manejará el Parse del XML con un Pull
	@Override
	public List<String> handleResponse(HttpResponse response)
			throws ClientProtocolException, IOException {
		try {

			// Creo el Pull Parser
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = factory.newPullParser();

			// Configuro la entrada del Parser para que sea el XML obtenido por Http
			// response es la respuesta obtenida del acceso Http
			xpp.setInput(new InputStreamReader(response.getEntity()
					.getContent()));
			
			// extraer el primer Parser event e iterar hasta el final de XML
			int eventType = xpp.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {

				if (eventType == XmlPullParser.START_TAG) {
					startTag(xpp.getName());
				} else if (eventType == XmlPullParser.END_TAG) {
					endTag(xpp.getName());
				} else if (eventType == XmlPullParser.TEXT) {
					text(xpp.getText());
				}
				eventType = xpp.next();
			}
			return mResults;
		} catch (XmlPullParserException e) {
		}
		return null;
	}

	//Este método indica qué estoy "parseando", hora o nombre
	public void startTag(String localName) {
		if (localName.equals(HORA_TAG)) {
			mIsParsingHora = true;
			tieneHora = true;
		} else if (localName.equals(NOMBRE_TAG)) {
			mIsParsingNombre = true;
		}
	}

	//Dependiendo de qué estoy "parseando", hora o nombre, guardo en una variable o en otra
	public void text(String text) {
		if (mIsParsingHora) {
			mHora = text.trim();
		} else if (mIsParsingNombre) {
			mNombre = text.trim();
		}
	}

	//Al acabar el evento vuelvo a poner los indicadores de tipo de "parseo" a false
	public void endTag(String localName) {
		if (localName.equals(HORA_TAG)) {
			mIsParsingHora = false;
		} else if (localName.equals(NOMBRE_TAG)) {
			mIsParsingNombre = false;
		} else if (localName.equals("wpt")) {
			//Cuando se acaba un waypoint, configuro el texto de su elemento de la lista
			if(!tieneHora){mHora = "Sin datos";}
			mResults.add("Nombre:" + mNombre + ", Hora de paso:"
					+ mHora);
			mNombre = null;
			mHora = null;
			tieneHora=false;
		}
	}
}