package com.spade.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import com.spade.PageActions.Testexecutor;

public class ColourRequestCR {

	@Test
	public void test() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		 System.out.println("under ColourRequestCR test");
		 Testexecutor te=new Testexecutor();
		te.executetest(this.getClass().getSimpleName());
	}

}
