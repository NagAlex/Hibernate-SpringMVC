package org.nag.mvc;

@SuppressWarnings("serial")
public class ImageUploadException extends RuntimeException {
	public ImageUploadException(Throwable cause){
		super(cause);
    }
    
    public ImageUploadException(String message){
        super(message);
    }
    
    public ImageUploadException(String message, Throwable cause){
        super(message, cause);
    }
}
