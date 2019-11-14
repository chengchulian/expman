package com.example.expman.reptile;

import com.example.expman.entity.heimao.HeiMaoAddressEntity;
import com.example.expman.entity.heimao.HeiMaoZipCodeEntity;
import com.example.expman.utils.TwoClassCode;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName HeiMaoZipCodeReptile
 * @Description 黑猫区码爬虫
 * @Author 程方园
 * @Date 2019/11/5 15:33
 * @Version 1.0
 */
@Component
public class HeiMaoZipCodeReptile {


    /**
     * @Description 获取HeiMaoZipCodeList
     * @author 程方园
     * @date 2019/11/6 11:31
     * @params [heiMaoAddressList]
     * @return java.util.List<com.example.expman.entity.HeiMaoZipCodeEntity>
     */
    public List<HeiMaoZipCodeEntity> getHeiMaoZipCodeList(List<HeiMaoAddressEntity> heiMaoAddressList) throws IOException {

        List<HeiMaoZipCodeEntity>  heiMaoZipCodeEntityList= new ArrayList<>();

        String requestBodyHead = "ctl00%24ContentPlaceHolder1%24ToolkitScriptManager1=ctl00%24ContentPlaceHolder1%24ToolkitScriptManager1%7Cctl00%24ContentPlaceHolder1%24btnQuery" +
                "&ContentPlaceHolder1_ToolkitScriptManager1_HiddenField=" +
                "&q=%E7%AB%99%E5%85%A7%E6%90%9C%E5%B0%8B" +
                "&ctl00%24ContentPlaceHolder1%24PostCode%24ddlCounty=";
        String requestBodyEnd = "&__EVENTTARGET=ctl00%24ContentPlaceHolder1%24btnQuery" +
                "&__EVENTARGUMENT=" +
                "&__LASTFOCUS=" +
                "&__VIEWSTATE=CUotEfzz0e2EYh8lFgKvH8wJ%2BhPwauvE5NYyuY1fcolbl8az%2BSlQE%2BZcOn354HlpIneDkQNi5S5u0D2ErhPLFOV77DWy6IHe2FUQbXOAhNdoS9sRAckarcc5%2FogD3z%2Fwx9g7wgdSooRbKF66HYloVRkWJpkq%2BEWcpKILGyEZzWDBO8EjwFr0faunLly9QO%2FGbJO%2FWj6V14eF55OhJC9U4LgO%2FFCZngBJBQ2SXx9MRFCIVnCH5LbsWY2DGYqqyDFgiGtHDBMmNdQyq56pVcbpX%2B%2BPxORIXGnaMPFa6LwSeIBqGsVqrCc1T%2BXYsvJ5NqBLDorp%2BRskjxwMtMsPBRafbI2Vd9CSJdqfViRWOcpPMjuT11iwOnwTmNhslTX8XKjOlVo3sz4HOt6u5Gw5ycPUHdtmyTKyo%2FQuL3fdaxk5ZzNTxiQfHVJ5gqxGRqkgnmuYoTK480dk2qhyvw3HskjP%2FRjG81VqotLMc6CIpp5c1fZNntnfS0%2FewVWULBzaNGmxO%2BE7cYKUxz293v%2BIYihjkKx9iBeTjqLIdU%2BtlTGLe4JTTKLr6IXJzNZmWgQrf9GHimpyIWOVo6T94rSQ1wbtcYAhlrZA3L%2B%2FpmvP0iG8RJcaQ%2FGJz7%2BsR%2BYbjJ%2BbGyJ8NWNXGy2iMRwUTS0z7xR1%2B5yr7EiWm9CjMk7zbdUMgTXoRsKo%2BFAuVGYK3JmhEHg54s137t8%2F3qAaJWvUqKeO6huxfmebC24RXdBCan15OJtek8TClC1oEjkIHdcYbN3IuJ0iPIFOVWrn6CorHxs524XbNHClNLz0VrP0VCZT0GgAZ0CYwFAOXTaAbYr%2Bawujgjjm7Q%2FWhhavsfknFW7bnRXoc2LbfK4%2FfkY5IwwJdlyBNVFxaTpcz8Jy8b0yr6TaqLmOAEw6IIIhcqgJQSlveG2JpUD93uhhjclrN0uaSaIhn4%2FaGkFc%2FsFfqNxpJwC0G5QdUg42X31l3UGRXuiiKt1sgAu2AJiM6oPLOZ082eM%2BYS3NCx5WbMiR%2B%2FyFOSPT2x83JvhqGMBLcsVUYONB6VeK82jwCukMm9VBQeF47vyZWS2B42hvnRx7ZOOLvMFqHDX6AMqTDRckEWYYMHLOZPMzU%2BlYwdRIGlSoJfK%2BubzqTBFaCFG5t%2FalJp1NaL9q2UdZFc%2BvNp8yeqWlqPGeCuvFB5AGIrAaONzTcArkr4y%2FWEcEUKOBuYTxtovMJktenoQmpV8ilUt1jKllmy1P0jsC%2FA8meeUL9guD2chpGBRuhvozIJmmOaAd3OFKPkMyiIioXDuvMx52ygSZkN5XvK0y%2F6vuezMz5s%2BBeRtOhSYtmF%2Fd7k0dI7YvfDE2odDl5yMFgtQuRj6CdjbZXg8eJh3nLdBKpsCurtHyi79Af1c0ARzqY17IpHFQbZDdytbvODcxP2yd3eL1KvC%2FG4NVnpX4OYSsB7rtM9k9xgFIErYDT79tQ9TyWejvjZSAVDRNKwf7L%2B0lBOPD9O%2BDihLSoU7rknHFpjzMc3DXPppSstUj5F0AbQK3aEEHfESiA4vaoi%2FqJvy0urAPay91nfZtZGV7XqTqiSR66vMbL28pzUVgvpjjfQlAFehXL%2Ba7OABk1VBcqw%3D%3D" +
                "&__VIEWSTATEGENERATOR=6EB3E046" +
                "&__EVENTVALIDATION=QlvdIQgClAgVwCnC2bX3gBL3Ve2UYx74sfjt%2BS8pktubNiCwrKK35aw0pagwnfk1PebwzbTeO1u8M8g5UjPOY7xQl9P5Dp4q%2FvpWM3APdJmzbxgq6dm%2BHq%2FW0R5nTgn9kI7d2lHPdi50mcHBS6jqZOpkBhC4TgIpBR8TDPaX%2B45InDpjHc1TsjH2%2Fs%2Ba7GZCGihs2xka28h27EvAqZZB03dvUIsuSKgMbO2e8jDFeTA4uj20IxiKCj%2BLHbJmm7Bk4kk5EgNA5TJgLYwoeSr0ySGdlrXOseNJJEN1YKGoUJLWU5AkKtAGtBtVUn90J87iPW6HkcG2SZbQTN4xm6YBzdZhS%2BAJgmbyiQEEs47g4CX%2BxkfjpFzFwvC98SsN9zMcnS5JhiAODtI%2BY80rJnTFi1u2NOkt2NH%2F4KDMEP6ya4%2BfgOw5bY%2FTEkWR1UztP3YFShpwBy18Gib0HLJbEuQJ1d1lb7HU2Mp6SoxrvGfeJ5FRyGqdYL25LAvqm68XrUpkO%2FRxtdfl3qDGwHfgoHEf0s1bUOT%2B6OSylEgMV0ip2jx1MecyXqyKKVxOVfrC7LgFS60AXTFFEzgnJwVMygP2Sexj7k7xJrOOE6Mw5bNBd00n2vruAXi6wPUS0iebkSeUH9%2FWXpqL4TD1nhwiA0EWeKbY17ojJUXlPuvWQ%2FLOowZgZACQsQFa0W%2BDAKKHRhZAkO3MztCxPQgPADi5Ezn0JlG%2Btum443CPq7iDdeObIz6sz7rKhEuNpoaDnw%2FHHvAT5MgkX3wLBw8GiBBcUkbOyUX5zS7I2CWHYs0PVvXoyVeip7GBv2y5dRnWqLA%2FnK1pcxtgIc2fw0ZChjawtG4mWwMRvmbwwApy4%2F10V%2F2S5DQisvxqbC%2F%2FHntojMBx%2F%2FhGVnoqg0F2Ufkp7z99TCQT5%2Fb4uWdnJ8TT2xi2Er%2Bn8BD7JP5GwOnQ3ttj7qsrPUzt38qKckTV4%2FfQ36Tsayz9mnzzZLCWj3gMm2meNkj0F9sPNnjNpQND46bgpl%2BBDLcKw%2FHrkIxsaJBVLtMLvQUYSRcx%2BDUl1qkVtwtm6iXFgvuNXIza2Fg3VHYjjgwVivu5vvzQG7u7QDmojgT98Udw7PWdvrFAVpN0Stj8aqa1wZZ0CGdpQnPRrZeVJjebUmkEn4d%2FTIyu9EK6nuTCwaU9iQ611KlgXH84x6T9pBbqjtBbWFF38mBvPthn%2F2ZI995VO0hE1NQjv24mRB5ZJX8pkroky0HLeNq3T5W38rG7WMYWLR9SKGg9JI2RoJt1x%2BAxCypHERPvYXqpDmOQnxmP0P6U61TYo9VvUZQfDYXQ6zoYFLYECX1G2zSrAO5bj7LPHMqdVM6q8TcE7IV0KXpTV4DC6T%2FIaaCBjOLIBLnVGAybmbtTs2L2%2BZuajd92dpMvzMe%2FTerFhte%2FKYowJZaU1qVrr%2BC%2BurkW7NBIJMvFqQDMNWng19%2B6fBGUCbGvBsrUhW8v%2FA9L7UWZl%2BVbG1lwuapastvu%2BesWhyzpz1gLqDhbpy30%2FFCcGawnXpxAR7iLtYXmDbSDFkxRWdkjFPA56SNPoudekVbB897z5l5HE0vqub%2BqdiuxgoFJBNzyZltcrHBfOgpfQXQ9rIe1V%2FbzSAJPJjXZIvKeaEeG0SCNXaWzeXf2laPPvGS8t5KHwEeAXeh8ltwolvbyaHFG%2BCa0xBliSonLrzQLMICdr5VqUHDy6ZaXgbK7JS1t%2F5n%2F0g5Z%2Ft6fjPB%2BTC%2BoHd69I6T4h6neCcR2Zbx4%2F0KYANpxaJBfHIAbzorpUlgEr5RRnyqdkY8Q7wniIpAqGW11QZ%2F%2FI8Aofl1UnI1r6h49vmcEddSZKU5zuXaKlNDQtyrSPkpx18eGDOVAisebYzoJxU6NiaDojCTmtkNxO2aApTWZcF2T7%2Bn2cyGUMnck6F%2B5aU8APNA0ewlThuKnW9EcIl%2B5tPA9IcSiBfZlOZf8%2BARS%2F3NPU9HEMwWek1erbKEInXsgOshl0gIS7i2bRKpk9d09ckaPzUpPrqorgZjU7n59KAimSudcmuAqZ4MCiTdbP04dwD13EKq4XeqPfKBx%2FLnJ6UKAgjO87hmlDKsPfZCDkKOchbt09A%2FhIN7sl3RjcxuT9hm0ag9H8Xh5SV3k4WiBumQQQhvVvNlNSEMrPDHKH5JejK1MK0Xl8C9g1tz4fKWh%2FRAffqs2MuXJR%2FcIoYKG95TQdDrcz7OpotwpgKc%2FEmafJ5ua6n7SyacHAjIDwneimCrfxriZ1np9%2BIq3byEasuXYw0IhBTa%2F3kqllLAnoGQmb5uL2YALwjygec95wHdmM%2BGBew9NwKSUhtQgpirUpwULRwcTrpXFl8fDDZIjF%2Fzo1jnituWG5JY7beo7Uu%2FoVVulbr%2F3L%2FP1O4Uwig3sdydW94rVdoKHTNgaSaDteELG7C7HOmqkxKlRNzKyRZUldQHRC%2FJH6iwHU%2F2dfmH32zMi%2F6lCi47RqUB5MwdFE96aJVKSzSdTwndVH1q8FQ%2BBcRlHTppMy3jqbDuAChs4HMI1Oa0%2FM0Ad20PCL5daqKeQGRLxNbCrH0ddi1MzTPK8mcE0%2F2rB6%2FMs6WcKT83D%2B8T8%2BfgiqpxPIVziQN9Cbm7F2xME%2BLcrPSlMGbK7LICjdFk3v28nMaTTLixD6yQdqlzNLljYikqpos7mjCsp3UB8TnosWv%2FLjKkfwCfOk%2Fb%2FxPyrM4%2BrI6sfCv5ckOlCaBOHK54Y1WNbZ3m5nf5xH%2Bh6MmTvs3vESH%2FRBNwfbT8LV3dieofmGkaBWxZf0JvgFm4T35iXTMSNnJuPbkoam3FvREAgsuisEKuvhRzsYubzi5W2Ha54%2BVvicm8NJawvtJDLUReJa65B9MMie0OfxWk95GEr60tvsOg3JSbFZWPdJ9a5wE0OpyYQr4RpW7O7cMUSJKYYxFAAy1sV1p3I54101%2BFDEEZXUdKU10xfYb1ID0wIgnbVLUb%2FiJ9wOQ%2FfP%2BjCRpRmViPmH3qJc8tONsk%2BJMtUUHEJZHFNR3MsA%2FpHBaH69i2SgfK1uyCAsvwCVapBS8u8PzosgAAlQXLUzYkocDaiC0Qie1hHjs5%2FB0XXvdS40aLupCkDrj3exy4KRN9FdC9EP81EDdC7dxd%2BEWhBsLkiAXe7tqP45%2Ftp6%2Fy%2BD2cXjaH6denxPm1ZWpxI%2FuYSlHPgDc%2BJFMgdoTOOPYb9w9RzxtEw2ljmb79Ej%2FnYdeEkdMwZv0NPTDtFm3iru9cND7JVrUApAQJ2KsSEuSmHBRqkx2I1vXxYGpDqaEKlXBQwyBjWVllVCTYpkVcUFiCn4wMHE3b1hh8BKXa0vFq7iuRr5izWKcM0%2Bt%2FbtuxNUc%2FsXnmK3vYejW%2BJ633HYxLCwDjbxz03KyLZx7BwnWgUSd187PgoAx3O4%2FSCJp7YsVu4KX2s3i2AoLwM6retUe2tUrA0dh2fx2tni4eHduNUQUbhmfa8Wi6BVm2i9b53z3m3I1KaO3O9poUTQrNMt4VLnZzSxBJf4yiMUOQ1mPsEvkQIzLRlGboYzM8BHh68tZgIkiYYvWr5TcnEJtmLesNT59JG7wR9qipLyVJWMaeg5kB1KzL8vzxpAPoJFnsQPX1coBrfiXkTR9RNyPCojzFR%2BgDuaA%2BKCzXBF4HDptTjAZPTYNjoDatsTYXaWdARJmAUVu2tKGNO4kXkD3JAE%2BQ40Ptm38wH%2BZWKvyCi128TUqU2rDtb4C2UB4rkXKp634mA6RaAgsSkfnXm%2BqTAyBVUvybWkpYKn6ILYpAcgnEU0cNWqnfXM9qVzGToRlZR5nU6SUWxUQQnttH6pAAhqYDMS%2FR84huRqCMSsXckALMLA0Q4cjLSQlTFjPOcBq2YRyTX%2B6A9Xi1ugXweAknQ8YPRIC6%2FrHofdwptcY0uLcRagrP1KGeqjZ6v58%2BYpCKugctWAhqbJWfnpvf44KRrs3iaEf85SGERYlfdFuPqfStQubjKORY176817ULUV7qJyx2JifqEa2kS4BG5rcWNJZbRlAyVsscCpDz7U4VJG%2Fl7U06ilutq7jwgz%2F9wpd4InQlmDCmRIOEGdCuUF3WVrVJiQxV3Hs6G0mFEVG21eQde%2BlatMEiPfmWogsQp2CmyV4Talf9pqc43I25IOezzX7u7VaBQW1QQWfM%2FY%2FXqI%2BUbidfTok8EE9%2FHIdW7hn6KVfjkOpiZAPciRg3PPIr%2Brc84E%2BFihB8cD46phbHCdUXLWhRwFTqwwxL6i%2FMGvLvrupz6lt7DgbBQdrEbRip8g1zAPzSLx6c7kZAwVtWb4tjvtImmh1x9oMFw4zvf%2FNK5d6ygSYKfrOfkybrlNnDtOLyy%2BBeGRQfMKS6eb9JHiniH41atXbu86xISatg2J38kEmPrk3N6a%2Brb%2BfJWpwxsdy4lYaLnbMMRmPV3yvgmkdH1g%2FpiYYdLUfUvxwirvn3gGqEdrDGz%2FJQuVZl1gWh247C999KcNM%2FwEEAvAoURzkQBEsqQ2hzgJRZoibOg6mf0GlEpb2PEtpMEVgJV8Qxz1J6t9RWuUSy9RmUUu7%2BNmjV%2BW41UZOdhzbwBkmd1ifEze6bKIQuvs96a3zAIR3TI9yiSYdnlrTuDR65ZDyQs4TzXIbvYaqL8GudVdYHJJRFpsXXcGcrrAfyIYiRwAX8ifnSa234ztF0ephNIhDIoN86JP%2FIiqaU4paTrYiSUmd9%2Bvc%2B0LFQYnQFexHmRkVuGPsB6UgKxLKE1n7cR7wv15FP%2FM7hMOtm7UbhIYTLkA6ZCAjDQV48%2FIzKhbqbhioRpoYy42yQA79A%2BeJwGWEWD8PglF6%2B74eRR7IXMop2MZYC9HCsrtYSqoNR3G1vi99GnF0WU5hcnKasi1htfXZJxcdNJghl3EUQU2ifYFNcNJaHMRz2muj%2FW4YmbnICFqaNsQqtdYKL%2FWd6fPF4Y34RIKipweFhbHyvoz1MGmnkkk6%2FL7Vu8KHL%2FaMMYNqpllQmZ8bmsnZBZxC4NUORvVWIwQJK%2BqVYL8D22jwaC2%2FhYF8sXcqK7WqpeMyRBARXO%2BXeR%2B9%2FANcHnNoyt6TlQXSCTJVgJB4jKZQaiFAOLlwYhdYjmzSZlc1qJDRr3g8ILWvS8KHo9PjCdJM8Yc8AoDqBoy6Oxbf2fO7H9o00GQI0zqlfNwuyC82zbLRj9nwzWgbqKcLCYiAAbH5Vaz95IZS846dBnT9mknGShGLljAQfDnCUtL9nYQtxY%2Bul2N3eOBSNMw4APhKvXEzljLoZ38uIu7iKeVEPjji%2FVTfiQYdOZG0zkwEjZQlYzKrIh%2BVfOxPak%2FQXoxNHYpCF6jn%2F1xrChk5d3XGTGgU3RGsz%2FdlKAREm5XeWu0U86Qm54w8j4q5cmIKHrpG0Hjhu7%2BYbrRAuJvOSeO4%2BmC3LQYPHagX2CWmODiaPJcNZeY5x2qrwnEKC0Y4IRTpiBomv20Aa7Z5YdzwSx7Du%2B5R%2B6mkLu5M88XIV0Df3nGJCiJ51AMkIZsIL3OMcUWXR6Ynk7SDfc4R8shCM%2B49jA6ULidbFkY%2FQABatiad3yKq2Ht2nQX07LEHjDzPGLvaMikRfR%2BMJ6rYiXn6ok2Ecla7Mq%2B6IdaFpI5ZNZdhns1BiskxrGCCmheHcs2Oqwi%2FN3gOOedbBD1rKyZEmRsAaKtP6Hrxm5Bq1wTOvi%2BFnD6sb5s%2Bzl6a2qd2KU%2B4fhhE15ue%2FSoZB%2B%2BvSKtEVGTXpV7tZImgfal31beKjpQAEgV7kFOPhTU6yOmd5%2FyPjeVx4krzAHTVajQh2ou%2FK1DnaifzbmevLDu1AkBOTmvxpk6WODQZfqQqr1Vg9yRKfGy1ttmESGas2fwtg91udcw7hPuT0oU63ns5X0%2B%2BvHOGhWBU9IZ5AzVjww4joMhLUJA7hrBU4%2FahuxNOtdCA%2BC%2FkQXZTp3Hw3zCFtDEeWv9jdV1CrKmKddZ6Ptb91Aj%2FEh5oWkszC3GtdL1gRtyogWezm2KuFR3g9UTklweGxN3YGBzQNlzH4baGtNcxcfGzcRSs22nLvZLUCQihAbkxHjvfdOgILVT0QKln4rvmklvxnT%2F88gb2aJ%2BDFl6pnGrk%2BKyvPCqV%2FcPNc9jJ7sL9rVPr46FBeVQW29cOWdp0U%2Bt4AKr4qQqvTcTgnK0np3whpL7ltTo5lssTKRB3DQtfBf5Y4KVDNeEIK0jvECTbzTBNS6OCJwfQdpJEz7mVBWGkQPPM7qbheBuvQR6xx3gcVbHPinqub8NVj5Lf4O5hCWUK2M8cYaGdzAmT5IJczcQIUAMYp80GkNXgncaqPnG0CdyU4E4J3o9R6az1gzFSsjwyv%2B96Tk7vT5PeWwFGlOqFrjxs7Z70OKmHVj6OcHb%2FE32slCbCsYlTMPovYwA5pDFqD2QhRbsbO5GmdH45w3mhsqanVJkKD952K2T8At7kGew%2FSnNPN5PDSage9PfzhaoMW4%2FrrS1wGT5usN9nNls3cpg1MSpjASEAyVFrbHC6jSnzsxW%2BxHnOBmZn3Xr4MSMCbEULtQvS2i0KlljShFGzrsu4audfsqzuRZx2tJbpw1z%2FcAZcuTpq5rc%2FF3rd%2FPvIuq%2Fw1CwxbAyy5m5QH0ejD%2Fp9aSLvm12NZidTaTvFOl0ngeU6xjVrn%2F2ew0x1Vl2uMOlQpsKV1QFXCpAOCthldSIvHZMEj%2F8zDSG83sN0JSreyIFgzt%2FRLk4aN%2FfH%2BZjKYxnTHws50FxqRPXR4uXOe99q%2FCKJM9FamUkMerluA3lnPgQqtnq5%2BRX37STpdUSf%2BMd1YGHAHQFPA2%2F2xLr2oS1SG83xDjCdEs4cS5gxa8p2lF5Zsp9XbpkH49Ajb46aXaMgtZhHnTQC5h9rXe5UkKznGqRcqfN%2Bm%2FouzAWdWNJpT7lhgFMoBBO0iRsvDJxdrY%2FNmii12KRNY%2F7Fv%2BikCRy8B21rSU12wibB%2Fi9hasD5o1hIlmxh2EqRrcI81M6q6T4eLQ%2FJG2Kw6bOItlIVwI3n6NRw9FcMRbA2lhAXgkX5GmW4c1EzvReHy6LwaO08kXAJR0skZcoiNchVkRSG9Na%2Fdxs1%2BcuEkbPwNqt2pLiBb11qip2ZIA5OaJ76CVZsCUBN%2F6ycWDYP8bWq2cZRHflSOPRDOuEjQiua5vyjYG5K7K4f3vk%2Fz9ldwHwt6LoJeM7XHRudBzo0p5yChECXMRSKq5k2xTqkjmNmq3nvv06axOo9TOTy6GmfWZeRnYvv7IaQVelGFfbFMpKtFNQirHEDRpjQMK3801UOu4HdzJP%2FZn1yHhtmdtVCjQ5LywrllvUtNFAIT5e2VsxAOYLF6z8XP4OO094Y2O9jkL9Gsbia0qzdaOkOIB50Ydn8qeLYKbzpWK3wxs9IC6uldq9jYC9YsR5lREvaukHG6CJqIBn2mbNKacu67L9%2BKlg%2B0RfwcshpBCBbGMoPTKWYwxCZhE1RhjifP8HH724aKIk2FqSJTNhVTYyQE40CkPDcCNE%2Fuv2Uz0fyGfOS4lTW9RlWBxAHHsu6N4BgndrHE0iV7MmU5SJ7YgkLDzDxbEAj3YMD0g6sxYn7e%2FuKgDKfkmHasNasFSQgVAiCxAywrl68E1nF5fdFmZg9WXDLU%2BoZfmEiMUoUlWOUaRzxUeNYgWujlt68FtxvZz0PQJtw7VXekqSTeZJCjlIiM9SuKkmGJf4NbRcimviJpIGrxsI1xQi1u1%2FTr4dFlGjF5UhcwhGvbwzgNsqsdfU69shGEJtjx30dw1Gd%2BzRmhtxWk9QA%2FfL%2FWBSrM4N4hvTP636WFeZFsa0p37%2Fzf%2F2%2FHROYEpR2dS8DyifQoOPbxaN111BNUw9okR%2BG3pA6sX50qYQzf4uI3sL%2BPbmxdRpxouF64zTBI%2FKPTAqcqhZsUFG3XNiU72qq7T3s91vWf0aqIzMtVV1NPLhjIPYCePqnmF5NMgbnQyubULj8Tl84r6PCREYVY3woCWmsTY4YhjSgkB2neRwAEqPP94aDQkEnHNy5DoMVrFowYs5nb6QzGRMP0hTpmbBOEQq70rb41VW%2B%2FDxoLC7kjdtagqCFe2RvKoBt4GMRxBe3rRe%2BtKERG6GkP0tE8bHTx30xrbNca9iDawCt%2FCys37Qm%2FoCLKSRkPs8APTBH8z40fwg210WSFzXyKwXkPhoukivI%2Bf3bmgyEOZPCkMCezrjuly2ma1aX3MJBOx3C7J3fyswZtMAJMe7UHJj9SM8asHygPA%2BHCQGnncEWLEY6MSiZ4olxPpA7ZwZALWkKPmfFT%2F0z5w1r0JjX5euGmB2%2Fwl47Bjfeq5WvBdXisEcw1kULMfhzfp0TTCc3rE1SIziRQob2iURF0juEDZNetyPWGIu0E%3D" +
                "&__ASYNCPOST=true" +
                "&";

        //循环请求
        for (HeiMaoAddressEntity heiMaoAddressEntity : heiMaoAddressList) {


            //拼接请求

            //获取请求结果
            String content = requestBodyHead +
                    heiMaoAddressEntity.getOneClassAddress() +
                    "&ctl00$ContentPlaceHolder1$PostCode$ddlCity=" +
                    TwoClassCode.getCode(heiMaoAddressEntity.getTwoClassAddress()) +
                    "&ctl00$ContentPlaceHolder1$txtAddr=" +
                    heiMaoAddressEntity.getIntactAddress() +
                    requestBodyEnd;

            try {
                String result = requester(content);
//            System.out.println(result);
                //处理请求结果
                heiMaoZipCodeEntityList.add(HeiMaoZipCodeEntity.builder().code(resultHandler(result)).address(heiMaoAddressEntity.getIntactAddress()).build());
            } catch (Exception e) {
                heiMaoZipCodeEntityList.add(HeiMaoZipCodeEntity.builder().address(heiMaoAddressEntity.getIntactAddress()).remake("生成失败").build());
            }
        }



        return heiMaoZipCodeEntityList;
    }


    private String resultHandler(String result){
        Document document = Jsoup.parse(result);
        Element code = document.selectFirst("#ContentPlaceHolder1_lblOfficeCode");

        return null!=code?code.text():"";
    }


    /**
     * @Description 请求者
     * @author 程方园
     * @date 2019/11/6 11:19
     * @params [content]
     * @return java.lang.String
     */
    private String requester(String content) throws IOException {

        OkHttpClient client = new OkHttpClient();
        //表单类型
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, content);

        //请求头设置
        Request request = new Request.Builder()
                .url("https://www.t-cat.com.tw/Inquire/Office.aspx")
                .post(body)
                .addHeader("host", "www.t-cat.com.tw")
                .addHeader("connection", "keep-alive")
                .addHeader("content-length", "11557")
                .addHeader("origin", "https://www.t-cat.com.tw")
                .addHeader("x-requested-with", "XMLHttpRequest")
                .addHeader("cache-control", "no-cache")
                .addHeader("x-microsoftajax", "Delta=true")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36")
                .addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("accept", "*/*")
                .addHeader("referer", "https://www.t-cat.com.tw/Inquire/Office.aspx")
                .addHeader("accept-encoding", "gzip, deflate, br")
                .addHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8")
                .addHeader("cookie", "__utmz=8454064.1572921542.8.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; ASP.NET_SessionId=vlla5gzawmfgzzrdfu5xirvo; __utmc=8454064; citrix_ns_id=iP4x3FCOSTMxaKCzLj8IH0SdapU0002; __utma=8454064.1157522952.1572517015.1572934865.1572938238.10; __utmt=1; __utmb=8454064.8.10.1572938238")
                .addHeader("postman-token", "bd0ef5b1-7e2d-fe0a-5c5f-27356d373db3")
                .build();
        request.isHttps();
        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());

        //获取结果
        //
//        Document result = Jsoup.parse(response.body().string());
        return Objects.requireNonNull(response.body()).string();

    }




}
