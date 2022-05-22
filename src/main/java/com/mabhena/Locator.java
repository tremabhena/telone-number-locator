package com.mabhena;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

/**
 *
 * @author Treasure S. Mabhena <treasuremabhenaATgmail.com>
 */
public class Locator {

    private Map<String, String[][]> teloneTowns;
    private Exchange[] teloneExchanges;

    @Getter
    @Builder
    public static class Exchange {
        private String exchangeCode;
        private String exchangeName;
        private int startNumber;
        private int availableNumbers;
        private String generalArea;
    }

    private Map<String, String> map(String[] keys, String[] values) {
        Map<String, String> map = new HashMap();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }

    public boolean isValidTelephoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 10) {
            for (Map.Entry<String, String[][]> entry : teloneTowns.entrySet()) {
                String areaCode = entry.getKey();
                for (String prefix : entry.getValue()[0]) {
                    if (phoneNumber.startsWith(areaCode + prefix)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String locateTelephoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 10) {
            for (Map.Entry<String, String[][]> entry : teloneTowns.entrySet()) {
                String areaCode = entry.getKey();
                String[][] value = entry.getValue();
                Map<String, String> map = this.map(value[0], value[1]);
                List<String> prefixes = map.keySet().stream().sorted(Comparator.comparingInt(String::length)).toList();
                for (String prefix : prefixes) {
                    if (phoneNumber.startsWith(areaCode + prefix)) {
                        return map.get(prefix);
                    }
                }
            }
        }
        return null;

    }
    
    public Exchange locateTelephoneNumberExchange(String phoneNumber){
        if(phoneNumber.length() == 10){
            //restOfnumber is phoneNumber without the exchange code
            int restOfNumber = Integer.valueOf(phoneNumber.substring(3));
            
            for(Exchange exchange : teloneExchanges){
                if (phoneNumber.startsWith(exchange.exchangeCode)){
                        if (restOfNumber >= exchange.startNumber && restOfNumber < exchange.startNumber + exchange.availableNumbers){
                                        return exchange;
                        }
                }
            }
        }
        return null;
    }

    public Locator() {
        this.teloneTowns = new HashMap<>();
        teloneTowns.put("024",
                new String[][]{
                    new String[]{"2", "213", "215", "214", "2150", "21"},
                    new String[]{"Harare", "Ruwa", "Norton", "Arcturus", "Beatrice", "Chitungwiza"}
                });

        teloneTowns.put("027",
                new String[][]{
                    new String[]{"203", "2317", "205", "2046", "204"},
                    new String[]{"Birchenough Bridge", "Checheche", "Chimanimani", "Chipangayi", "Chipinge"}
                });

        teloneTowns.put("020",
                new String[][]{
                    new String[]{"20", "21", "24", "200"},
                    new String[]{"Mutare", "Dangamvura", "Penhalonga", "Odzi"}
                });

        teloneTowns.put("026",
                new String[][]{
                    new String[]{"209", "208", "2098"},
                    new String[]{"Hauna", "Juliasdale", "Nyanga"}
                });

        teloneTowns.put("025",
                new String[][]{
                    new String[]{"206", "2055", "207", "205"},
                    new String[]{"Murambinda", "Nyazura", "Headlands", "Rusape"}
                });

        teloneTowns.put("066",
                new String[][]{
                    new String[]{"210", "210", "219", "218", "219", "219", "212", "216", "217", "2137"},
                    new String[]{"Bindura", "Centenary", "Concession", "Glendale", "Mazowe", "Christon Bank", "Mount Darwin", "Mvurwi", "Guruve", "Shamva"}
                });
        teloneTowns.put("067",
                new String[][]{
                    new String[]{"2192", "2198", "2196", "2136", "21", "215", "214", "214"},
                    new String[]{"Darwendale", "Raffingora", "Mutorashanga", "Trelawney", "Chinhoyi", "Murombedzi", "Mhangura", "Banket"}
                });
        teloneTowns.put("061",
                new String[][]{
                    new String[]{"214", "215", "2141", "2140"},
                    new String[]{"Kariba", "Karoi", "Makuti", "Chirundu"}
                });
        teloneTowns.put("068",
                new String[][]{
                    new String[]{"21", "215", "2189", "216", "21"},
                    new String[]{"kadoma", "Chegutu", "Chakari", "Sanyati", "Selous"}
                });
        teloneTowns.put("065",
                new String[][]{
                    new String[]{"2080", "23", "208", "21", "213"},
                    new String[]{"Macheke", "Marondera", "Wedza", "Murewa", "Mutoko"}
                });
        teloneTowns.put("039",
                new String[][]{
                    new String[]{"2", "234", "2380", "2366", "2360", "230", "245", "2323", "235", "2308"},
                    new String[]{"Masvingo", "Jerera", "Nyaningwe", "Mataga", "Mberengwa", "Gutu", "Mashava", "Nyika", "Zvishavane", "Chatsworth"}
                });
        teloneTowns.put("031",
                new String[][]{
                    new String[]{"231", "233", "2337", "2370"},
                    new String[]{"Chiredzi", "Triangle", "Rutenga", "Ngundu"}
                });
        teloneTowns.put("029",
                new String[][]{
                    new String[]{"2", "2861", "2821", "2803", "2802", "2800", "2804", "2807", "2809"},
                    new String[]{"Bulawayo", "Tsholotsho", "Nyamandlovu", "Turkmine", "Shangani", "Esigodini", "Figtree", "Kezi", "Matopos"}
                });
        teloneTowns.put("081",
                new String[][]{
                    new String[]{"28", "2847", "2835", "28", "2875", "2856"},
                    new String[]{"Baobab", "Binga", "Dete", "Hwange", "Jotsholo", "Lupane"}
                });
        teloneTowns.put("083",
                new String[][]{
                    new String[]{"28"},
                    new String[]{"Victoria Falls"}
                });
        teloneTowns.put("085",
                new String[][]{
                    new String[]{"23"},
                    new String[]{"Beit Bridge"}
                });
        teloneTowns.put("089",
                new String[][]{
                    new String[]{"280"},
                    new String[]{"Plumtree"}
                });
        teloneTowns.put("084",
                new String[][]{
                    new String[]{"2801", "2835", "28", "2808"},
                    new String[]{"Filabusi", "Collen Bawn", "Gwanda", "West Nicholson"}
                });
        teloneTowns.put("054",
                new String[][]{
                    new String[]{"2", "252", "2532", "2548", "212"},
                    new String[]{"Gweru", "Shurungwi", "Mvuma", "Lalapanzi", "Chivhu"}
                });
        teloneTowns.put("055",
                new String[][]{
                    new String[]{"25", "25", "25", "259", "2558", "2557"},
                    new String[]{"Kwekwe", "Redcliff", "Battle Fields", "Gokwe", "Nkati", "Munyati"}
                });

        this.teloneExchanges = new Exchange[]{Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Arcturus")
            .startNumber(2142000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Athlone")
            .startNumber(2448000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Athlone")
            .startNumber(2492000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Avondale Host")
            .startNumber(2307000)
            .availableNumbers(2000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Avondale Host")
            .startNumber(2371000)
            .availableNumbers(9000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Avondale Host")
            .startNumber(2302000)
            .availableNumbers(3000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Avondale Host")
            .startNumber(2339000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Avondale Host")
            .startNumber(2332000)
            .availableNumbers(6000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Avondale Host")
            .startNumber(2326000)
            .availableNumbers(4000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Beatrice")
            .startNumber(2150200)
            .availableNumbers(700)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Belvedere")
            .startNumber(2740000)
            .availableNumbers(2000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Bluffhill")
            .startNumber(2310000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Bluffhill")
            .startNumber(2331000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Bluffhill")
            .startNumber(2305000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Borrowdale Host")
            .startNumber(2850000)
            .availableNumbers(6000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Borrowdale Host")
            .startNumber(2858000)
            .availableNumbers(2000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Borrowdale Host")
            .startNumber(2870000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Borrowdale Host")
            .startNumber(2880000)
            .availableNumbers(7000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Braeside")
            .startNumber(2782000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Braeside")
            .startNumber(2742000)
            .availableNumbers(2000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Chitungwiza")
            .startNumber(2121000)
            .availableNumbers(11000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Dzivarasekwa")
            .startNumber(2216000)
            .availableNumbers(2000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Eastlea")
            .startNumber(2746000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Eastlea")
            .startNumber(2776000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Eastlea")
            .startNumber(2788000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Epworth")
            .startNumber(2577000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Glen Norah")
            .startNumber(2680000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Glen Norah")
            .startNumber(2613000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Glen View Host")
            .startNumber(2645000)
            .availableNumbers(5000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Glen View Host")
            .startNumber(2682000)
            .availableNumbers(7000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Glen View Host")
            .startNumber(2690000)
            .availableNumbers(8000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Gunhill")
            .startNumber(2744000)
            .availableNumbers(2000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Gunhill")
            .startNumber(2783000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Harare Airport")
            .startNumber(2575000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Harare Airport")
            .startNumber(2585000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Harare Soft Client")
            .startNumber(2000000)
            .availableNumbers(11200)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Hatfield Host")
            .startNumber(2570000)
            .availableNumbers(5000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Hatfield Host")
            .startNumber(2578000)
            .availableNumbers(7000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Hatfield Host")
            .startNumber(2586000)
            .availableNumbers(3000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Hatfield Host")
            .startNumber(2500000)
            .availableNumbers(10000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("024")
            .exchangeName("Highfield")
            .startNumber(2689000)
            .availableNumbers(1000)
            .generalArea("Harare")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Bellevue")
            .startNumber(2460000)
            .availableNumbers(20000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Bulawayo")
            .startNumber(2260000)
            .availableNumbers(20000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Bulawayo")
            .startNumber(2320000)
            .availableNumbers(2000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Bulawayo")
            .startNumber(2330000)
            .availableNumbers(1000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Bulawayo")
            .startNumber(2360000)
            .availableNumbers(1000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Bulawayo")
            .startNumber(2880000)
            .availableNumbers(10000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Cowdray Park")
            .startNumber(2560000)
            .availableNumbers(10000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Esigodini")
            .startNumber(2800200)
            .availableNumbers(700)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Figtree")
            .startNumber(2804000)
            .availableNumbers(900)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Hillside")
            .startNumber(2240000)
            .availableNumbers(10000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Kezi")
            .startNumber(2807200)
            .availableNumbers(700)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Killarney")
            .startNumber(2290000)
            .availableNumbers(10000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Luveve")
            .startNumber(2520000)
            .availableNumbers(10000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Mabutweni")
            .startNumber(2400000)
            .availableNumbers(20000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Matopos")
            .startNumber(2809200)
            .availableNumbers(700)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Nkulumane")
            .startNumber(2480000)
            .availableNumbers(20000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Northend")
            .startNumber(2200000)
            .availableNumbers(20000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Nyamandlovu")
            .startNumber(2821200)
            .availableNumbers(700)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Pumula")
            .startNumber(2420000)
            .availableNumbers(20000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Queensdale")
            .startNumber(2227000)
            .availableNumbers(2000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Queensdale")
            .startNumber(2226000)
            .availableNumbers(1000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Riverside")
            .startNumber(2280000)
            .availableNumbers(10000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Shangani")
            .startNumber(2802200)
            .availableNumbers(700)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Suburbs")
            .startNumber(2250000)
            .availableNumbers(10000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Suburbs")
            .startNumber(2230000)
            .availableNumbers(10000)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Tsholotsho")
            .startNumber(2861200)
            .availableNumbers(700)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("029")
            .exchangeName("Turkmine")
            .startNumber(2803200)
            .availableNumbers(700)
            .generalArea("Bulawayo")
            .build(),
            Exchange.builder()
            .exchangeCode("067")
            .exchangeName("Banket")
            .startNumber(2142000)
            .availableNumbers(2000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("066")
            .exchangeName("Bindura")
            .startNumber(2106000)
            .availableNumbers(2000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("066")
            .exchangeName("Centenary")
            .startNumber(2102000)
            .availableNumbers(1000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("068")
            .exchangeName("Chakari")
            .startNumber(2189200)
            .availableNumbers(700)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("068")
            .exchangeName("Chegutu")
            .startNumber(2152200)
            .availableNumbers(3300)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("067")
            .exchangeName("Chinhoyi")
            .startNumber(2121000)
            .availableNumbers(9000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("061")
            .exchangeName("Chirundu")
            .startNumber(2140200)
            .availableNumbers(700)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("066")
            .exchangeName("Christon Bank")
            .startNumber(2195500)
            .availableNumbers(1500)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("066")
            .exchangeName("Concession")
            .startNumber(2192000)
            .availableNumbers(1000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("067")
            .exchangeName("Darwendale")
            .startNumber(2192200)
            .availableNumbers(700)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("066")
            .exchangeName("Glendale")
            .startNumber(2182000)
            .availableNumbers(900)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("066")
            .exchangeName("Guruve")
            .startNumber(2172000)
            .availableNumbers(1000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("068")
            .exchangeName("Kadoma")
            .startNumber(2131000)
            .availableNumbers(2000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("068")
            .exchangeName("Kadoma - Ngezi")
            .startNumber(2134000)
            .availableNumbers(1000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("068")
            .exchangeName("Kadoma - Rimuka")
            .startNumber(2122000)
            .availableNumbers(7000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("061")
            .exchangeName("Kariba")
            .startNumber(2145000)
            .availableNumbers(1800)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("061")
            .exchangeName("Karoi")
            .startNumber(2142000)
            .availableNumbers(1700)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("065")
            .exchangeName("Macheke")
            .startNumber(2080200)
            .availableNumbers(700)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("061")
            .exchangeName("Makuti")
            .startNumber(2141200)
            .availableNumbers(700)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("065")
            .exchangeName("Marondera")
            .startNumber(2320000)
            .availableNumbers(9000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("065")
            .exchangeName("Marondera")
            .startNumber(2728000)
            .availableNumbers(2000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("066")
            .exchangeName("Mazowe")
            .startNumber(2195000)
            .availableNumbers(500)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("067")
            .exchangeName("Mhangura")
            .startNumber(2145000)
            .availableNumbers(1000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("066")
            .exchangeName("Mount Darwin")
            .startNumber(2122200)
            .availableNumbers(2000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("065")
            .exchangeName("Murewa")
            .startNumber(2122000)
            .availableNumbers(3000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("067")
            .exchangeName("Murombedzi")
            .startNumber(2152000)
            .availableNumbers(1000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("065")
            .exchangeName("Mutoko")
            .startNumber(2132000)
            .availableNumbers(1000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("067")
            .exchangeName("Mutorashanga")
            .startNumber(2196200)
            .availableNumbers(700)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("066")
            .exchangeName("Mvurwi")
            .startNumber(2162000)
            .availableNumbers(1000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("067")
            .exchangeName("Raffingora")
            .startNumber(2198200)
            .availableNumbers(700)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("068")
            .exchangeName("Sanyati")
            .startNumber(2162000)
            .availableNumbers(1000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("068")
            .exchangeName("Selous")
            .startNumber(2144000)
            .availableNumbers(1000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("068")
            .exchangeName("Selous")
            .startNumber(2008500)
            .availableNumbers(2100)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("066")
            .exchangeName("Shamva")
            .startNumber(2137200)
            .availableNumbers(700)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("067")
            .exchangeName("Trelawney")
            .startNumber(2136200)
            .availableNumbers(700)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("065")
            .exchangeName("Wedza")
            .startNumber(2082000)
            .availableNumbers(1000)
            .generalArea("Mashonaland")
            .build(),
            Exchange.builder()
            .exchangeCode("055")
            .exchangeName("Battle Fields")
            .startNumber(2570000)
            .availableNumbers(1000)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("054")
            .exchangeName("Chivhu")
            .startNumber(2122000)
            .availableNumbers(2000)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("055")
            .exchangeName("Gokwe")
            .startNumber(2592000)
            .availableNumbers(2000)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("054")
            .exchangeName("Gweru Host")
            .startNumber(2220000)
            .availableNumbers(14000)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("055")
            .exchangeName("Kwekwe Main")
            .startNumber(2520000)
            .availableNumbers(7000)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("054")
            .exchangeName("Lalapanzi")
            .startNumber(2548200)
            .availableNumbers(700)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("055")
            .exchangeName("Mbizo 1 & 2")
            .startNumber(2540000)
            .availableNumbers(8000)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("054")
            .exchangeName("Mkoba 1")
            .startNumber(2250000)
            .availableNumbers(4000)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("054")
            .exchangeName("Mkoba 2")
            .startNumber(2255000)
            .availableNumbers(4000)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("055")
            .exchangeName("Munyati")
            .startNumber(2557200)
            .availableNumbers(700)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("054")
            .exchangeName("Mvuma")
            .startNumber(2532200)
            .availableNumbers(700)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("055")
            .exchangeName("Nkayi")
            .startNumber(2558200)
            .availableNumbers(700)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("055")
            .exchangeName("Redcliff")
            .startNumber(2562000)
            .availableNumbers(2000)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("055")
            .exchangeName("Redcliff")
            .startNumber(2568000)
            .availableNumbers(2000)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("054")
            .exchangeName("Senga")
            .startNumber(2260000)
            .availableNumbers(3000)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("054")
            .exchangeName("Shurugwi")
            .startNumber(2526000)
            .availableNumbers(3000)
            .generalArea("Midlands")
            .build(),
            Exchange.builder()
            .exchangeCode("027")
            .exchangeName("Birchenough Bridge")
            .startNumber(2032000)
            .availableNumbers(1000)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("027")
            .exchangeName("Checheche")
            .startNumber(2317200)
            .availableNumbers(700)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("020")
            .exchangeName("Chikanga MSAN")
            .startNumber(2010000)
            .availableNumbers(1600)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("027")
            .exchangeName("Chimanimani")
            .startNumber(2052000)
            .availableNumbers(2000)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("027")
            .exchangeName("Chipangayi")
            .startNumber(2046200)
            .availableNumbers(700)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("027")
            .exchangeName("Chipinge")
            .startNumber(2045600)
            .availableNumbers(200)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("027")
            .exchangeName("Chipinge")
            .startNumber(2044400)
            .availableNumbers(200)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("027")
            .exchangeName("Chipinge")
            .startNumber(2042000)
            .availableNumbers(1500)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("020")
            .exchangeName("Dangamvura")
            .startNumber(2130000)
            .availableNumbers(2000)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("020")
            .exchangeName("Dangamvura MSAN")
            .startNumber(2030000)
            .availableNumbers(2100)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("026")
            .exchangeName("Hauna")
            .startNumber(2092000)
            .availableNumbers(1000)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("026")
            .exchangeName("Headlands")
            .startNumber(2072000)
            .availableNumbers(1000)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("026")
            .exchangeName("Juliasdale")
            .startNumber(2082200)
            .availableNumbers(2000)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("026")
            .exchangeName("Murambinda")
            .startNumber(2062000)
            .availableNumbers(1000)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("026")
            .exchangeName("Murambinda")
            .startNumber(2058000)
            .availableNumbers(500)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("020")
            .exchangeName("Mutare Fetex")
            .startNumber(2060000)
            .availableNumbers(10000)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("020")
            .exchangeName("Mutare MSAN")
            .startNumber(2020000)
            .availableNumbers(6000)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("026")
            .exchangeName("Nyanga")
            .startNumber(2098000)
            .availableNumbers(800)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("026")
            .exchangeName("Nyazura")
            .startNumber(2055200)
            .availableNumbers(700)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("020")
            .exchangeName("Odzi")
            .startNumber(2002000)
            .availableNumbers(1000)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("020")
            .exchangeName("Penhalonga")
            .startNumber(2422200)
            .availableNumbers(800)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("026")
            .exchangeName("Rusape MSAN")
            .startNumber(2050000)
            .availableNumbers(4500)
            .generalArea("Manicaland")
            .build(),
            Exchange.builder()
            .exchangeCode("039")
            .exchangeName("Chatsworth")
            .startNumber(2308200)
            .availableNumbers(700)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("031")
            .exchangeName("Chiredzi")
            .startNumber(2315000)
            .availableNumbers(2500)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("031")
            .exchangeName("Chiredzi")
            .startNumber(2312000)
            .availableNumbers(2200)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("039")
            .exchangeName("Gutu")
            .startNumber(2302000)
            .availableNumbers(2000)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("039")
            .exchangeName("Jerera")
            .startNumber(2342000)
            .availableNumbers(1000)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("039")
            .exchangeName("Mashava")
            .startNumber(2452000)
            .availableNumbers(1000)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("039")
            .exchangeName("Masvingo")
            .startNumber(2277000)
            .availableNumbers(1000)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("039")
            .exchangeName("Masvingo")
            .startNumber(2260700)
            .availableNumbers(9000)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("039")
            .exchangeName("Mataga")
            .startNumber(2366200)
            .availableNumbers(700)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("039")
            .exchangeName("Mberengwa")
            .startNumber(2360200)
            .availableNumbers(700)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("039")
            .exchangeName("Mucheke RSM")
            .startNumber(2252000)
            .availableNumbers(4000)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("031")
            .exchangeName("Ngundu")
            .startNumber(2370200)
            .availableNumbers(700)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("039")
            .exchangeName("Nyaningwe")
            .startNumber(2380200)
            .availableNumbers(1800)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("039")
            .exchangeName("Nyika")
            .startNumber(2323200)
            .availableNumbers(700)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("031")
            .exchangeName("Rutenga")
            .startNumber(2337200)
            .availableNumbers(700)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("031")
            .exchangeName("Triangle")
            .startNumber(2335000)
            .availableNumbers(2000)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("039")
            .exchangeName("Zvishavane")
            .startNumber(2355500)
            .availableNumbers(2500)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("039")
            .exchangeName("Zvishavane")
            .startNumber(2352000)
            .availableNumbers(3000)
            .generalArea("Masvingo")
            .build(),
            Exchange.builder()
            .exchangeCode("081")
            .exchangeName("Baobab")
            .startNumber(2830000)
            .availableNumbers(5000)
            .generalArea("Matabeleland")
            .build(),
            Exchange.builder()
            .exchangeCode("085")
            .exchangeName("Beit Bridge")
            .startNumber(2322000)
            .availableNumbers(2000)
            .generalArea("Matabeleland")
            .build(),
            Exchange.builder()
            .exchangeCode("081")
            .exchangeName("Binga")
            .startNumber(2847200)
            .availableNumbers(700)
            .generalArea("Matabeleland")
            .build(),
            Exchange.builder()
            .exchangeCode("084")
            .exchangeName("Collen Bawn (CBN)")
            .startNumber(2835000)
            .availableNumbers(200)
            .generalArea("Matabeleland")
            .build(),
            Exchange.builder()
            .exchangeCode("081")
            .exchangeName("Dete")
            .startNumber(2835200)
            .availableNumbers(700)
            .generalArea("Matabeleland")
            .build(),
            Exchange.builder()
            .exchangeCode("084")
            .exchangeName("Filabusi")
            .startNumber(2801000)
            .availableNumbers(1000)
            .generalArea("Matabeleland")
            .build(),
            Exchange.builder()
            .exchangeCode("084")
            .exchangeName("Gwanda")
            .startNumber(2829000)
            .availableNumbers(1000)
            .generalArea("Matabeleland")
            .build(),
            Exchange.builder()
            .exchangeCode("084")
            .exchangeName("Gwanda")
            .startNumber(2820000)
            .availableNumbers(6000)
            .generalArea("Matabeleland")
            .build(),
            Exchange.builder()
            .exchangeCode("081")
            .exchangeName("Hwange")
            .startNumber(2820000)
            .availableNumbers(5000)
            .generalArea("Matabeleland")
            .build(),
            Exchange.builder()
            .exchangeCode("081")
            .exchangeName("Jotsholo")
            .startNumber(2875000)
            .availableNumbers(1000)
            .generalArea("Matabeleland")
            .build(),
            Exchange.builder()
            .exchangeCode("081")
            .exchangeName("Lupane")
            .startNumber(2856000)
            .availableNumbers(2000)
            .generalArea("Matabeleland")
            .build(),
            Exchange.builder()
            .exchangeCode("089")
            .exchangeName("Plumtree")
            .startNumber(2805000)
            .availableNumbers(3000)
            .generalArea("Matabeleland")
            .build(),
            Exchange.builder()
            .exchangeCode("083")
            .exchangeName("Victoria Falls (Airport)")
            .startNumber(2840000)
            .availableNumbers(10000)
            .generalArea("Matabeleland")
            .build(),
            Exchange.builder()
            .exchangeCode("084")
            .exchangeName("West Nicholson")
            .startNumber(2808200)
            .availableNumbers(700)
            .generalArea("Matabeleland")
            .build()
        };
    }
}
