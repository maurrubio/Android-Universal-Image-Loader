package com.nostra13.universalimageloader.core.decode;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.media.ExifInterface;

import com.nostra13.universalimageloader.core.decode.CompatibleImageDecoder.ExifInfo;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.nostra13.universalimageloader.utils.L;

@SuppressLint("NewApi")
public class CompatibleImageDecoderReflection {

	public static ExifInfo defineExifOrientation(String imageUri) {
	    int rotation = 0;
	    boolean flip = false;
	    try {
	            ExifInterface exif = new ExifInterface(Scheme.FILE.crop(imageUri));
	            int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
	            switch (exifOrientation) {
	                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
	                            flip = true;
	                    case ExifInterface.ORIENTATION_NORMAL:
	                            rotation = 0;
	                            break;
	                    case ExifInterface.ORIENTATION_TRANSVERSE:
	                            flip = true;
	                    case ExifInterface.ORIENTATION_ROTATE_90:
	                            rotation = 90;
	                            break;
	                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
	                            flip = true;
	                    case ExifInterface.ORIENTATION_ROTATE_180:
	                            rotation = 180;
	                            break;
	                    case ExifInterface.ORIENTATION_TRANSPOSE:
	                            flip = true;
	                    case ExifInterface.ORIENTATION_ROTATE_270:
	                            rotation = 270;
	                            break;
	            }
	    } catch (IOException e) {
	            L.w("Can't read EXIF tags from file [%s]", imageUri);
	    }
	    return new ExifInfo(rotation, flip);
	}
}
