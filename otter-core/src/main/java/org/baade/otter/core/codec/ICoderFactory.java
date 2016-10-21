package org.baade.otter.core.codec;

public interface ICoderFactory {

	
	public IDecoder getDecoder();
	
	public IEncoder getEncoder();
}
