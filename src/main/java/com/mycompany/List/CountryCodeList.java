/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.List;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Danyal-PC
 */
public class CountryCodeList {
    
        private static final String[] AsianCode = {"AF","AM","AZ","BH","BD", "BT", "BN", "IO", "KH", "CN", "CX", "CC", "CY", "TL", "GE", "HK", "IN", "ID", "IR", "IQ", "IL", "JP", "JO", "KZ", "KW", "KG", "LA", "LB", "MO", "MY", "MV", "MN", "MM", "NP", "KP", "OM", "PK", "PS", "PH", "QA", "SA", "SG", "KR", "LK", "SY", "TW", "TJ", "TH", "TR", "TM", "AE", "UZ", "VN", "YE"};
        public static final Set<String> AsianCodesSet = new HashSet<String>(Arrays.asList(AsianCode));
        
        private static final String[] AfricaCode = {"DZ","AO","BJ","BW","BF","BI","CM","CV","CF","TD","KM","CD","DJ","ED","GQ","ER","ET","GA","GM","GH","GN","GW","CI","KE","LS","LR","LY","MG","MW","ML","MR","MU","YT","MA","MZ","NA","NE","NG","CG","RE","RW","SH","ST","SN","SC","SL","SO","ZA","SS","SD","SZ","TZ","TG","TN","UG","EH","ZM","ZW"};
        public static final Set<String> AfricaCodesSet = new HashSet<String>(Arrays.asList(AfricaCode));
        
        private static final String[] EuropeanCode = {"AL","AD","AT","BY","BE","BA","BG","HR","CY","CZ","DK","EE","FO","FI","FR","DE","GI","GR","GG","HU","IS","IE","IM","IT","JE","XK","LV","LI","LT","LU","MK","MT","MD","MC","ME","NL","NO","PL","PT","RO","RU","SM","RS","SK","SI","ES","SE","CH","TR","UA","GB","VA"};
        public static final Set<String> EuropeanCodesSet = new HashSet<String>(Arrays.asList(EuropeanCode));
        
        private static final String[] OceaniaCode = {"AS","AU","CK","TL","FJ","PF","GU","KI","HM","FM","NR","NC","NZ","NU","MP","PW","PG","PN","WS","SB","TK","TO","TV","VU","WF"};
        public static final Set<String> OceaniacodesSet = new HashSet<String>(Arrays.asList(OceaniaCode));
        
        private static final String[] SouthAmericaCode = {"AR","BO","BR","CL","CO","EC","FK","GY","PY","PE","SR","UY","VE"};
        public static final Set<String> SouthAmericaCodesSet = new HashSet<String>(Arrays.asList(SouthAmericaCode));
        
        private static final String[] NorthAmericaCode = {"AW","BS","BB","BZ","BM","VG","CA","KY","CR","CU","CW","DM","DO","SV","GL","GD","GT","HT","HN","JM","MX","MS","AN","NI","PA","PR","BL","KN","LC","MF","PM","VC","SX","TT","TC","VI","US"};
        public static final Set<String> NorthAmericacodesSet = new HashSet<String>(Arrays.asList(NorthAmericaCode));
        
        private static final String[] AntarcticaCode = {"AQ"};
        public static final Set<String> AntarcticaCodesSet = new HashSet<String>(Arrays.asList(AntarcticaCode));
        
}