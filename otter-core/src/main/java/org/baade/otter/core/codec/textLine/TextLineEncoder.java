package org.baade.otter.core.codec.textLine;

import java.nio.charset.Charset;

import org.baade.otter.core.codec.IEncoder;
import org.baade.otter.core.session.ISession;

public class TextLineEncoder implements IEncoder {

	private Charset charset;

	public TextLineEncoder(String charsetName) {
		this(Charset.forName(charsetName));
	}

	public TextLineEncoder(Charset charset) {
		this.charset = charset;
	}
	
	@Override
	public void encode(ISession session) {
		// TODO Auto-generated method stub

	}

}
