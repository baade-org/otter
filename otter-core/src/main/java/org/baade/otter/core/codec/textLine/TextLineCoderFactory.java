package org.baade.otter.core.codec.textLine;

import java.nio.charset.Charset;

import org.baade.otter.core.codec.ICoderFactory;
import org.baade.otter.core.codec.IDecoder;
import org.baade.otter.core.codec.IEncoder;

public class TextLineCoderFactory implements ICoderFactory{
	
	private IDecoder decoder;
	
	private IEncoder encoder;
	
	public TextLineCoderFactory(String charsetName){
		this(Charset.forName(charsetName));
	}
	
	public TextLineCoderFactory(Charset charset){
		this.decoder = new TextLineDecoder(charset);
		this.encoder = new TextLineEncoder(charset);
	}

	@Override
	public IDecoder getDecoder() {
		return this.decoder;
	}

	@Override
	public IEncoder getEncoder() {
		return this.encoder;
	}

}
