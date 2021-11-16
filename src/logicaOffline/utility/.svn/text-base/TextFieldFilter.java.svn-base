package logicaOffline.utility;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextFieldFilter extends PlainDocument {

	private StringBuffer cache = new StringBuffer();
	int maxLenght;

	public TextFieldFilter(int maxLenght){
		this.maxLenght = maxLenght;
	}

	public void insertString(int off, String s, AttributeSet aset) throws BadLocationException{
		int len = getLength();
		if(len >= this.maxLenght) {
			return;
		} //MAX CARATTERI

		cache.setLength(0);
		char c;
		for(int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			cache.append(c);
			if(cache.length() >= maxLenght - len) {
				break;
			}
		}
		if(cache.length() > 0) {
			super.insertString( off, cache.toString(), aset);
		}
	}
}
