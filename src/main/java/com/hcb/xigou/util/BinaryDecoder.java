package com.hcb.xigou.util;

public interface BinaryDecoder extends Decoder{

	 byte[] decode(byte[] pArray) throws DecoderException;
}
