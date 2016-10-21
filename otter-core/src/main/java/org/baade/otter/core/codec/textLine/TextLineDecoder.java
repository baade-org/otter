package org.baade.otter.core.codec.textLine;

import java.nio.charset.Charset;

import org.baade.otter.core.codec.IDecoder;
import org.baade.otter.core.session.ISession;

public class TextLineDecoder implements IDecoder {

	private Charset charset;

	public TextLineDecoder(String charsetName) {
		this(Charset.forName(charsetName));
	}

	public TextLineDecoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	public void decode(ISession session) {
		// TODO Auto-generated method stub

	}

}
