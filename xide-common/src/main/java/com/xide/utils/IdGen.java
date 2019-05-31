package com.xide.utils;

import org.apache.commons.lang3.StringUtils;
import java.util.UUID;

public class IdGen {

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return StringUtils.remove(uuid, "-");
    }

    public static String get24UUID(){
        UUID id= UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[4];
    }

    public static String get28UUID(){
        UUID id= UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[2]+idd[4];
    }
}
