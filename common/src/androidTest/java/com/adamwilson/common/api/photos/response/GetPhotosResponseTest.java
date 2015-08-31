package com.adamwilson.common.api.photos.response;

import com.adamwilson.common.model.Image;
import com.adamwilson.common.model.Photo;
import com.google.gson.Gson;

import junit.framework.TestCase;

public class GetPhotosResponseTest extends TestCase {

    public void testValidResponseNotNull() throws Exception {
        assertNotNull(getValidResponse());
        assertFalse(getValidResponse().isEmpty());
    }

    public void testParseResponse() throws Exception {

        final String validResponse = getValidResponse();

        final GetPhotosResponse response =
                new Gson().fromJson(validResponse, GetPhotosResponse.class);

        assertNotNull(response);
        assertEquals(1, response.current_page);

        assertNotNull(response.photos);
        assertEquals(1, response.photos.size());
        Photo photo = response.photos.get(0);
        assertNotNull(photo);

        assertEquals("Calgary", photo.getUser().getCity());
        assertEquals("2015-08-07T19:35:37-04:00", photo.getHighestRatingDate());

        assertFalse(photo.isForSale());
        assertFalse(photo.isNsfw());

        assertEquals(12371, photo.getTimesViewed());
        assertEquals(99.7, photo.getRating());
        assertEquals(-114.065551757812, photo.getLongitude());

        assertNotNull(photo.getImages());
        assertEquals(1, photo.getImages().size());

        Image image = photo.getImages().get(0);
        assertNotNull(image);

        assertEquals(440, image.getSize());
        assertEquals("https://drscdn.500px.org/photo/117390219/w%3D440_h" +
                             "%3D440/6ddd2921bbff1292075c055df057f6b7?v=2", image.getUrl());
        assertEquals("https://drscdn.500px.org/photo/117390219/w%3D440_h" +
                             "%3D440/6ddd2921bbff1292075c055df057f6b7?v=2", image.getUrl());
        assertEquals("jpeg", image.getFormat());
    }

    private String getValidResponse() {
        return "{\"current_page\":1,\"total_pages\":1000,\"total_items\":11309,\"photos\":[{\"i" +
                "d\":117390219,\"user_id\":100726,\"name\":\"Michelle\",\"description\":\"a " +
                "little throwback Thursday from a ring session with Michelle.  Check my " +
                "links. ♥\\\\\\n\\\\\\n<a href=\\\"http://instagram.com/thephotofiend\\\">" +
                "Instagram</a>  | <a href=\\\"http://www.fb.com/photofiend\\\">FB</a> | <a " +
                "href=\\\"http://iso.500px.com/diy-lighting-ring-tutorial/\\\">Ring Light " +
                "Tutorial</a> | <a href=\\\"http://www.photofiend.com/#!store/c1cnz\\\">LR " +
                "+ PS Presets</a> | <a href=\\\"http://www.photofiend.com\\\">www.photofiend" +
                ".com</a> | <a href=\\\"https://plus.google.com/u/0/108394991730508740353/" +
                "posts/p/pub\\\">G+</a>\",\"camera\":\"NIKON D800\",\"lens\":null,\"focal_" +
                "length\":\"35\",\"iso\":\"200\",\"shutter_speed\":\"1/640\",\"aperture\":\"1." +
                "8\",\"times_viewed\":12371,\"rating\":99.7,\"status\":1,\"created_at\":\"2015" +
                "-08-07T00:05:35-04:00\",\"category\":7,\"location\":null,\"latitude\":51.1009" +
                "349390346,\"longitude\":-114.065551757812,\"taken_at\":\"2014-11-03T14:25:19" +
                "-05:00\",\"hi_res_uploaded\":0,\"for_sale\":false,\"width\":1400,\"height\":1" +
                "849,\"votes_count\":1379,\"favorites_count\":696,\"comments_count\":47,\"n" +
                "sfw\":false,\"sales_count\":0,\"for_sale_date\":null,\"highest_rating\":99" +
                ".7,\"highest_rating_date\":\"2015-08-07T19:35:37-04:00\",\"license_type\":0" +
                ",\"converted\":27,\"collections_count\":0,\"crop_version\":2,\"privacy\":fal" +
                "se,\"image_url\":\"https://drscdn.500px.org/photo/117390219/w%3D440_h%3D440/6" +
                "ddd2921bbff1292075c055df057f6b7?v=2\",\"images\":[{\"size\":440,\"url\":\"ht" +
                "tps://drscdn.500px.org/photo/117390219/w%3D440_h%3D440/6ddd2921bbff1292075c0" +
                "55df057f6b7?v=2\",\"https_url\":\"https://drscdn.500px.org/photo/117390219/w" +
                "%3D440_h%3D440/6ddd2921bbff1292075c055df057f6b7?v=2\",\"format\":\"jpeg\"}" +
                "],\"url\":\"/photo/117390219/michelle-by-the-photo-fiend\",\"positive_votes_" +
                "count\":1379,\"converted_bits\":27,\"watermark\":false,\"image_format\":\"jp" +
                "eg\",\"user\":{\"id\":100726,\"username\":\"thephotofiend\",\"firstname\":\"T" +
                "he Photo\",\"lastname\":\"Fiend\",\"city\":\"Calgary\",\"country\":\"Cana" +
                "da\",\"usertype\":0,\"fullname\":\"The Photo Fiend\",\"userpic_url\":\"htt" +
                "ps://pacdn.500px.org/100726/6a3135c37186de36a944b7cbd44f3fdfe8670ba2/1.jp" +
                "g?0\",\"userpic_https_url\":\"https://pacdn.500px.org/100726/6a3135c37186de" +
                "36a944b7cbd44f3fdfe8670ba2/1.jpg?0\",\"cover_url\":\"https://pacdn.500px.or" +
                "g/100726/6a3135c37186de36a944b7cbd44f3fdfe8670ba2/cover_2048.jpg?30\",\"upgra" +
                "de_status\":2,\"store_on\":true,\"affection\":301907,\"avatars\":{\"defau" +
                "lt\":{\"https\":\"https://pacdn.500px.org/100726/6a3135c37186de36a944b7cbd44f" +
                "3fdfe8670ba2/1.jpg?0\"},\"large\":{\"https\":\"https://pacdn.500px.org/10072" +
                "6/6a3135c37186de36a944b7cbd44f3fdfe8670ba2/2.jpg?0\"},\"small\":{\"http" +
                "s\":\"https://pacdn.500px.org/100726/6a3135c37186de36a944b7cbd44f3fdfe8670ba" +
                "2/3.jpg?0\"},\"tiny\":{\"https\":\"https://pacdn.500px.org/100726/6a3135c371" +
                "86de36a944b7cbd44f3fdfe8670ba2/4.jpg?0\"}}},\"licensing_requested\":false}]" +
                ",\"filters\":{\"category\":false,\"exclude\":false},\"feature\":\"popular\"}";
    }
}