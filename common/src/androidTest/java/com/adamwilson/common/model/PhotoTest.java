package com.adamwilson.common.model;

import com.adamwilson.common.api.ImageSize;

import junit.framework.TestCase;

import java.util.ArrayList;

public class PhotoTest extends TestCase {

    public void testGetPhotos() throws Exception {
        Photo photo = new Photo();

        assertNull(photo.getImages());
        assertNull(photo.getImage(4));

        ArrayList<Image> images = new ArrayList<>();
        photo.setImages(images);

        images.add(new Image(ImageSize.CROPPED_70x70px, "", "", ""));
        photo.setImages(images);
        assertEquals(1, photo.getImages().size());
        assertNotNull(photo.getImage(ImageSize.CROPPED_70x70px));

        photo.setImages(null);

        final int[] validImages = ImageSize.getAll();
        assertNotNull(validImages);
        assertAllNull(photo, validImages);

        photo.setImages(getValidImages());
        assertNoneNull(photo, validImages);
    }

    private void assertNoneNull(Photo photo, int[] validImages) {
        for (int validSize : validImages) {
            assertNotNull(photo.getImage(validSize));
        }
    }

    private void assertAllNull(Photo photo, int[] validImages) {
        for (int validSize : validImages) {
            assertNull(photo.getImage(validSize));
        }
    }

    private ArrayList<Image> getValidImages() {
        ArrayList<Image> images          = new ArrayList<>();
        int[]            validImageSizes = ImageSize.getAll();
        for (int validImageSize : validImageSizes) {
            Image image = new Image(validImageSize, "URL", "URL_HTTPS", "FORMAT");
            images.add(image);
        }
        return images;
    }
}