package com.memoirs.travel.travelmemoirs;

/**
 * Created by mat on 22.10.2015..
 */

//U ovoj klasi su pohranjene sve konfiguracije za online bazu i hosting
public class OnlineDbConfig {

    public static final String DOMAIN_ROOT = "http://travelmemoirs.pctechlearning.tk/";

    public static final String MEMOIR_THUMBNAILS = DOMAIN_ROOT + "memoir/thumbnails/";

    public static final String MEMOIR_VIDEO = DOMAIN_ROOT + "memoir/videos";

    public static final String USER_IMAGES = DOMAIN_ROOT + "users/images/";

    public static final String TRAVEL_MEMOIRS_API = DOMAIN_ROOT + "travel_memoirs_api/";

    public static final String URL_ADD_USER = TRAVEL_MEMOIRS_API + "add_user.php";

    public static final String GET_ALL_EMAILS = TRAVEL_MEMOIRS_API + "get_all_emails.php";
}
