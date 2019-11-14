package com.example.expman;

import com.example.expman.entity.famiport.FamiportOrderUpEntity;
import com.example.expman.entity.famiport.FamiportResponseExpInfo;
import com.example.expman.entity.famiport.FamiportResponseResult;
import com.example.expman.entity.kerry.KerryOrderUpEntity;
import com.example.expman.reptile.KerryOrderReptile;
import com.example.expman.utils.ReptileUtil;
import com.example.expman.utils.TwoClassCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName test
 * @Description 测试类
 * @Author 程方园
 * @Date 2019/11/2 14:47
 * @Version 1.0
 */
public class test {

    @Test
    void date() throws IOException {

        String content = "ctl00%24ContentPlaceHolder1%24ToolkitScriptManager1=ctl00%24ContentPlaceHolder1%24ToolkitScriptManager1%7Cctl00%24ContentPlaceHolder1%24btnQuery&ContentPlaceHolder1_ToolkitScriptManager1_HiddenField=&q=%E7%AB%99%E5%85%A7%E6%90%9C%E5%B0%8B&ctl00%24ContentPlaceHolder1%24PostCode%24ddlCounty=%E6%96%B0%E5%8C%97%E5%B8%82&ctl00%24ContentPlaceHolder1%24PostCode%24ddlCity=221_23&ctl00%24ContentPlaceHolder1%24txtAddr=%E6%89%93%E8%B5%8F&__EVENTTARGET=ctl00%24ContentPlaceHolder1%24btnQuery&__EVENTARGUMENT=&__LASTFOCUS=&__VIEWSTATE=Md4bN6M46uKOuqS6%2FI96oBlevTssgWCvxWRY6YVk4b%2BdXAa%2Fh6dLhZ2gvjmRSF5sG29s24z0AOJWA5eDJTlvMMcrB9llnsPAfhqCLINF5lE1nzXq1IU6LkQouk%2BdkDRakRSMKFSE6du051pWLgMIJp06uTYxJ1WfgoCdpsuJbbnrT6qo2s1ub6hpjV2f%2FSjzOi%2Fs71VGUqT%2BVWmdSaVYVPVaNfHKTBp2UpTpjenUmxBoPOZ0PLpC0cgE4GV3QsFu56fppoUQXk6%2Fh17tUu0MQ%2F3l8cho4T7x4U01vPLEb9xSYe64ROpmYyM9l3c4UBsivUWNdwGN6eRTiswSLE%2FFdR1F9U6qoOOUukiZck66pXDurVkT9uwCXB6H9PFTJR2ZObKOYbiP8eaBaM6r2Kq4%2FDuu3wFLGG91%2F22Dq%2BKadyHGuiQJqgOL0nTlKrdZvjoZuN%2FCCJmRJAaLpSWl5YwrUbTIvFZxqEVjpVhk5rpT28449zPDXdhQJi3G3cYAogxImUIHJ61qKZ78zLb5%2FwSM12S4YyykLJ%2BCdAoLFSY%2B7MHksrUDJDDI56qvv544XmpY%2FlXSCVQgGPF4z6t1lip8UjvC9BWPYoFGAJA2KLbAS%2BZpoAvTBN2nHzLTFm5u8Mjvo6Uk2kktrhmULZF6alDV6vJYyBBpDdNHwAEv7%2BIRQa%2F3Fm6XZCxQ7kblgkmtgbiuSeZlXPi6O9wEetwga%2FJVhncE6%2B4mGVpXb1yQDNhHwBuaj0MdWDz%2B2Nh%2BYMyvVqCwNT0SxKgNsFb8FsiZ2LKFWsn4iSUhBg68ggUmuGWg%2FayeO71ImayAbRbWBYQhCaoxDuQNg23w7YULGw9CfUevkufcBrirtf%2BM5lFgP5TR2bw%2Fv9zpz2qZnBVQr4oVwcdnNXvyKQYdEfrlB3rVtYlRxq4cYCbSi2KX5j830r76P0KesKFu0t%2Fue%2FTgFLSN%2BpMie%2FBebONAgPGYQDKFPleQdR0kt9P9fdkOGvk%2F8NEnLeJfkIh3ci4Nq64m%2FSpDIeOdrdcjK%2F8%2B7CYwk%2FgGV3Qh%2B%2BKWXT9Crfg62Y5O%2F8NWC0KSyjHQHbc692GMEjGlHmTDno7q85%2B5cfX9SbLyxu9nvx0KbtafRV%2BTQHpLFnHBigD6FZE8QxTUvr9RsBU3%2Fbr4eXUH5Ixeal%2Bit%2FCgKsPCFvgqQMFfPuVOWUpn1ZAIjwPhsgxQnfiAWBl5uiZ9fspYeI4o7lbh6XoF0gMe%2BEGTRdISdT6chO960fEGFZDSu9Qq4aTjrKcQdbLRkCWbqYv9aNDBcMHt8l%2BPNTLLA933QQ%2FwEgfuyr5pkKItxOQMD68Ka9AqeR%2BWlkYgef9IIotfcn7V5iQiTdmNXyKlpLB61f%2FKcQypSTVhA9b92ygZs3%2BWI%2FqFgze%2Fcq1Q3QMTrDtW0KBraxqSffOenxjZORv2pAGJoWQPfUdgCqL4ygDg%2BvjE9WPias8Pu9X46%2FOAEZO7%2BC9SzzyuClUBcMySdwQxH14%2Bw3znb%2Bozq3cCrcnrQxlLusUKVRwx0E8rhnaZggCyKfTMB1T9id8xF1mA4x4mHdK%2B1dDrO%2FbbdcAhzRtuct9VQBozqcShaq4jbdHdtrWqjYW6507WBMo1ZeiA%2Fq7YUeBS9nE4hrpYStnwNBP1E28TLsB3f%2FJtskQDLPkhmKFCjDgwfQ%2FMX2pp3z0gOomtz0WO2LGO0CDequXrhwalmR6ynR2ub9Ur5G4a2O93RU1L3B%2B7CAcu5V6PkbiYXOuVMtlSbPyuK5IEf9LKxAylQLLORxZmTutZuNG35e4E4SEEDXhlVBD2gvAnfjqF1SDh83HiY9V%2BTtvTqbe1i0z3azMnDSufpWALIi3TUSgBFkUxT52L0PPLXMl2cz02I4DcQJuiBDYAfa9g2zAE1JET7Eu%2BU%2B4WIOeTpeKHZbpyozUTef5596PErY%2FGNuD6ibZkjVhbvsZzK1cb8zS%2FT9e0sxzUZLcEfooO5XVV88v%2Ffy7cJNtnpw%3D%3D&__VIEWSTATEGENERATOR=6EB3E046&__EVENTVALIDATION=Hzi7cM0XnU0kP3FbNcThQMtEZe2hhWhWYzH9jqjlfBZNZEOSdKyZ0%2F0YqCnHHV6KwLngIx3hIx3x2HidrAXP%2BLLBeUcGdDvOfSMemdE%2F3d6lbwAgIjtgDce%2BJRogWqhFra7D7TU%2FpJ4uTHHNjm1qCMnbhqM06Pgss5yktiwKcxXgU%2F%2FdEY9NqzPrIRn87vsQYPzbhI%2FsfJgFaB%2FaDa3yIdosB5MMIQX1%2FNUTdx86tgAksILh%2FVOOjtPheQkBMR3aH8M2dE%2FiuOUF%2Fts8vgYKFZHCr1K6SBYXg8pDxonrOGmRxeeMUppJjV3B9qYwMyae57zedc7%2BBBx5MJLuDYszegV9bPjOfbPF8tTHUGw%2F%2BQkGwlaB%2BQCkv0uSa%2F%2Fe%2F4CDurtv%2F0Qxf5Y0ru23fhvtvKkQcKY0DJbHexrz4VAHqv%2FA8LhHkrr5dl6%2Fd%2FNWN%2F08VRzOxG4CIlJvcyHIVKFyUZmvY3qeD3VV7xyeYU1NiQ3yWxIr6UPczcxIkOFxS09dcHfGQOA3ORbED1qa%2FMIOf%2FU3ZvOuZogEdGOI93YA%2FKYq7vM68SI8TNb2NZ30vbYOuNZvmo4tVCoXhUkZhPHfuiMTxXsnhOQf5rjyEG0eUVct1LVzt2IZcdTkzmsK%2F4JY1ekL5nW%2FJjgzflYx%2F%2FGytK%2B%2BJiYBaRZZoTi2rYOJTvADNTwhlwnsN%2BLtbpKm29tD%2FU%2F0IhDcMEtz5lC43MxWMaY6dmdoNgGsRNPOP5wy8%2FGVj3kf7TrOp7LfebS87ehMP2TnaOtyeq6TQsO6wRX%2FojFEMYmKcg1ac5ErTJU%2B%2BtTZXKtPDW1%2B0RiowcgKuiPnV3Bmd8sorkhsaPlGWLlKK1TZeyyj%2FDYvZW08BRHWrn6F8eS4qRZmERwUWiD0ck%2BjyaYuPBWKOcbPGLOBrIhaKSCt7Te8y8nTOedWlRSuxn47DCdEwDtE9bKYsFbS%2FTyre7kXbOxZ8tnO50IsRRcvz9iRx3sVlclPnC%2FTxO2eoszRhECa8DcH8db76EnaPkbantD9RhElo1Medg667V9BbnHgGqVKJW9Oql6ln0q9hOPJYzGLyuirgwnGyM5HjBG1p6sFi66gP0bJB4VeU%2BAdoNKnSUY2sloA90ZxiBVd1bL%2FbPT9MwOF6b7AcNlcjjHfmG0n7GqmmfHX%2FoPTET4h5OCFPGr8dfnenvnqTLxFc8grfdMSNTnBzl0x2G5ff0s%2FcMQygEuyKShJXE0Lf%2F7h7DXOeEGmRUtyiOWlMIdf8mWIU77KLd0vUk9fi42l91l9zonWlSGTOdKNCoWnLrvedM2DFCcWN6%2FCIbNTC1dnzRDdJrTO5xsiZN0k9c%2Be3Ad%2FQTP8oAHit2%2BODghc1L%2FgZ2pVeVATTK4rG29t8xFmZ%2BE1Slftb5T2nqIDtEavnFUHm9oxiefJOhi4AlTbHwx%2FYixZkLbNI7fjMHx9uEFrMB4OlyOp3hdyz%2FcEwqsg%2B7b6wCCCnDPPbe1I%2BNeTqdmOVsBO4d0VaZzgf5JbmdLpGM9eJhEmkze9dnu2OqPHEx6uflIxvgvU08122MqvL7CmRT0hLluL9XRoq4SWWf9PrV7tRDprtwdx03ulhvtIUq928l%2BLWNH8MZaAMuuvh9iLS9HjmhiwMzxYTG5mBJT1t3ymwlSmL0b6JhN8iTLui3ULvIQp2qSCtlUdV7Icqe4SXVXhtvam02cIA2d6symRAQh0kycRAEGNdQqExCf26KB2BOdqShuPUi0NeB64hjj2UlD34hQ1rBW%2Fh36NnAC3KvSK4%2FwjNrtLVNfML%2F7jdGPiWcOIR9wFlES57Kg3cb4SWyQ8VtTfhZYM9SuQ9CshGsm5wJhiyMzZJV6B0jZ3KHlQvLALX2ZHQnckajuQptww%2FOsG1RQicLEEgFcv0uTEWHr7%2FICAZIYpJvCn%2FhvjUWFHRlAwGtaA4N%2BtbGwSaMpssKhxA5aABWDPHIxPhXf6fJRWjjZW2GWOULTq6pV5tEGQNlX7lYFa5dSs1FZyTpVYXwoX3dcr599rSKZ4Q90XlnGSCSzixE%2FQa6%2FRPZIT11jC0O01gwWOeoHVGyg1NUyact8omQVIIhxQORTfhIPUwYvxyjlWpTdMf3ZcPG47qjz80OkiSxlxkBbagBeMtcWivlM9oHWZbiudt4jd5PqXgrxZPb451%2FBe0lixJ1ERjsLKx7NKJz88D2jIfLyOSBSodTWruC4X0ZRHFy7XuvJK9sC5GP%2BsTM2cNQY0XUi5m63xo38v4%2BbuKbCMNwI8kVafGBa6sRowUmK8Heqqd7u0g5KbE6qIAofPx5Uj58ApL%2BF%2BCV42kqxVKpdk5EvTtwJnLAvxSpoMeMEIXFeeHpNrp87OM2qw1NJTWiWRUSYIUdoYLMVhj1O7U4s5pbv%2FTHL%2BaFTrdBhyYfjamln5ZccmIAILY9z8WLyCB7opPHsAnOQkrotLqsLHGfxd6dFEaVtdLuPQiHnfWqMTs%2FPlVbLfMZMpR1%2BT0MyQywcbNOiy8T3vYZFOUICid3YgR6GnbxE5geTOLfmHcHU%2BIfuwsiNTNeXQ%2BtSx536H4CBmh0ZeRiiXCWR7c9bQZr5qR9%2BD7M2hE0lCEQpNpJ9dgNnXQ4gr%2FaImK%2BYRTLrYkPoP4mDeaIR%2BLKUo70weQxVI2P%2FoQLP8M3z2cwHqIiFtOX3lxg93H%2BmeWvcGMy65lnTRwYBzlb4IF1sikC%2F7cAdaxlJIFS5EAXEpQvDWt9On4rDpooXTKNeC%2BdjyxZSblseG3bKCJkOPK%2B8fgsTTo5wXHJgS4ROcrn5ngFyiDaZS4E%2BvZSnPizcrtlNfEkenh88FfcJT%2BzgZaUQfzhFl9Vwiw2Goe4wgIUbe3Iuo7todc60DdMp8ouzboa0IyNggcx7KKXqglCuLPgBsQRjdYCdL5GrTiFpKBSv%2BoD3dCgozAaXWMSxlk00Si4L78vAgI4TeiOoFgg0ar75TwMDAZrRpj5%2B4sfjeWkMIwK8IAcMOqpOzCudHW%2BDo4M4%2BmkOPf2I0l%2FIu%2BsRkiR%2FhmmjVYLToJ1Ul9kn5vZap5cDvUHOLC8YLNnwBbAW5bzKkoX1DX1olvNZ%2FSavCg26RV2dKyyb5V4r%2F2ztLE3j80499dIhiab87dTvdBJ6tt%2BzJWckKY29gV59rm1GNApk7yHvkKH9ZWfvvrod7GJ1c%2BwZW%2Bea3Q81bWiiE560mPVQ5TohSn871W1C9bgOJ42fISOU3jvffRzj5%2FvbjKoEVkmNsnQw8N4Ci8ggj5UguGpkXzRCynSKkF8QmENjQwJOtf%2BDL0%2FRsrFdM05X30iInnkILpVNFusLN4NIvlCRMyWveOGll2oVpJqS0d9HRJ0tSjcUpADiaQTwK6oEe0dszEwF%2F3Qiyn4wQXzNfzKmXzl5AZ1auFvvco2tWuThwI4L%2BpZsuE0IA8cPLae0P8TTohJSa%2FLDzaNEnL6yDEE26kPWFZbdZvMa4eU4CaDXBQMl7E%2FYf6svWqOdggxVg2bw8AaPfezBjCOAeEkGgWSq%2Fk1GrFsNh0NgKBXNoN%2BRyX%2Fensa226rBwYJ58bHpcj27ql65hYaqG3%2F%2BqybhODNmXkByFu%2FItA1pXK%2F1CbYQ%2Bz%2FY3ZPgWHyWKd9LgV9MyJE5%2B%2F1c%2F9DhJdJztc7yiOIBCYb42xfhmD4gUbx62x0vRLRFI2WSfWGOyw%2Bbzdz40aMGwfne1%2FANyqVWC5E4G7Q3rdbkXOWVsmuNaRjW4Vc%2BepBFUkmX1Gmp0iWgSsjzAeXnQopAirO%2BU2jo2kBi0NIz7cytqnnclOMLTeCeykG1GUMaYGKdXoqHAfxYbGwKUrdIEqzMsotrCY7RkTij02dhk3qWqIaE9NfEo0sVjicuj6rc%2Bs18%2FoR2ENnwXiCTdR7eVMJMvqzsx%2BcaiWRdQoV3QdohKDMz8pT4ClpZoFKXfpoUcwcKkYAQwLoChCtmL0OK5joh3dx3LDCFA5t9H7SQzuM9g9pCYj2q%2BkLFRmjYXA7RqWAzJmWA4GGvDUWMnxKai7PENRHLMqf4IOvwwSwzydPmx3%2FI4Nxn25ggn1CBE7XYf8SYXnLIAc2dRkXutdORua8%2BTGGbI2pEtcfmVFGB0dI3Stuubr0Mf5KXOp5Q1mNoCC2JlotaVIjnkY9XfacEtd%2FhdVWYgErFPoige0IvSRjeyyP6dJo94OYbj3%2F261kPxDDL17uQvgZs2VUpjpxDDrKoO8nQSxWx3LwsJ0S5qprsZQSmRz1sKuZvGytRtf%2Fnin9Eq3aYAZ7Ur8mu5dTJkTwnX%2Bp2MnL2ATUOM94oB%2FN4G8tUukj52O0ye7kna2QZD3V6bosBfZNpup8e30VdJBxPJcXM1NBvEuIxUC5JROeOZnCvcHbz0n%2F2yw6JQWqQpcEooUM0qJL%2BKyZ0aW%2BOk4QyjGgSkf%2F8zARqh%2FThF2L3l2WRp4m4fzaf%2BFizV22SnnE7kEUS8ialL%2BZg1RoDUqTuvQRKpajsu0UBkIHro6Gw5We4gkgEZHh6pvUvxWCtTNBFO9vggrg7PJSLrg17l0kcr1YksVrNJUWyb%2FiJwUSNLnWrEmHjUDjVkdHqmUHvnKPJHfYOqQy4hG3y3RaSGCDCvvcmNaIdSN215VrP3m%2BDfFPlER2ucuNSHua6%2Bk4CyRLOujTSZkCDo2TRalruFLfMHnnL6791571YSjV21VPUPNVlM6F%2FdGuynOws7HN%2BWXDAVcXSgxLBoSC2eoPjixJ%2FSGgp3o8xQHeKaAkx5zOLmnnPVIaLrcNB92DpGu8T%2F%2FHl6WWhn9GcUqUCGluCAiHhJU%2BmqmHS2gPPsE5Eo%2FazvoNZa6WmpSeM9WL%2FQArZj9hF301fCoeGt5crKUU1VeNUolKnGPeF2Ug%2BX9zjf5ftRALZ5yFTmDQrOabN8IugTHkud9C0k7nKpXl%2Bz1x9Ae5DiE%2F8N75hMydBvj3OwAGzMOu6Rir2zDUa0hdK%2FhI8hRPEbsBBWU6%2Blv60aL2Z4dd16Zuj12wTujhtDi%2Bvh4%2FOxeGKg3%2ByiqfKvENvD1pZHBJKDDQfRBLcFfKWiDRokG62QQLk4132rwvLdfpp7GGAOcJC23o4qaXN6LdwkzdAxJMaKQa%2F8euiidzp6crMUidbh8oRxWNHin6g8s7jPUldcFsMwDs8kqhnSc%2FiPXsvcihZp4gsdGbDfGR6nSjG8FHq90s1nQQvBnLfeg73L2ftAI6cDiQfV%2F%2Fg8QSBhwR7wUnrAtZ32dwITjjClxupCU90XFqvOBih2TN1%2Fusapa%2BJ6X3%2F0%2F%2BTAo%2Fw1Q4e90%2BmO7jRDaL2D4Rb%2Fz9lAb5Oa2KcY1oc6dJzIs37Ha966XwFRGN5X5judGpDVX5FJZxx6gBo5nNC1t2VJfYWN45b9IFm8JhgWaL4%2F7G7AK7LS8422%2BIqj7uuGqVSUmB%2BTGoRsuTu5hsJpC96D7OAY5rOxI9Y%2Fbv3n3Dooldl%2BBtUULNdR7zhf0xmozdur0xLgt5OCdz3MNIywJEV58RWWjqcpAunIsonlfTp3qdHtejUa2K96sNAWlw7VTP26CvcTESvyelsowlhaweTmIKeOzOWFmYJCXApfNnwyZ%2BIR6WkgEPAxBIoNUduZXJXlwQ4LkCzVfugIqm3OdnYoAC2WIKTOb5ywSakEsOT9vdTdBBs7lz4o8AaLM1ZGQjMnKkYuN%2B6rbzBU9wXMdn3SVttAvN7dkYpESTxCSVltNZQVG7P%2BC4Spfzh2qOezaPxYnxQb6LyoB5rKdROtocwkkvkc9K8xcL%2BuA9mJ1TZ1fUQNciZgv3rdUImYM1zYFa02ThFciMIFcKx5Yv3MswXh6t9WKKmvIZXiWoTTxeRpju%2FUmPXE2wp%2Bj1pOWgXXP3jrGsDXr5lMs9QONJqDvV%2B2zpZROGG4sNWZ5QkUuya4Eqh3NwX2jtSOsy%2BsEhTRJDylfp77%2BfbcwO5wVI3kCXvW0fGF3Xi9O755ubH85GOV5kPe2HVvmK9vEkzVc%2FvkuFygETaNh4EHQtAA4PotBf2eleNFxhFr2CiGeAiNZRUcnGmdAIJ8IctkLGJ5j2T8vMgKXBioxS5TPuXJEdCWKwnxjq1ydiv1pVFOR83jkJk8mQoNFRVjHn5lTAAiaWD01PbTkPEW4%2FrPD1Ndv9xR7oNtQ0exFaDW9GLtf0EtcSh5hRM%2FH3J5qpqskSrx%2BOVw9J6ZGRVgFR%2BLxGC8oDtOk7F4ePV4lUtxF2NcfJebqi0spu%2FSRh%2B1ZLX3o7H%2BOrT6uyPC4q0w2eyEHUVLu6KIHfnB5kR1TZTpoO5S0QtTqz%2B1hDhYIGguV7%2BGB2lpRZzWHozk09lGTQ8%2F18WIaQPCzkGWWsoo%2FM0LbUGeyM5Xl%2B9Lb1dLGwx1jovsu6WdpIf867BqTZge7yZXIxhDu%2FDjcakSFV3GG4tHz%2BwfLyEuafyWsRKr8ti%2FiCGvg26M9JOreQd1XdUMNyF4s3%2BLxBY%2BubWKgQjh1dCA4IFYvKu5zCIS0DRfL84cRqvoEtZgZMUmD1FfmuqQxXdnYnE%2FPMsuQpnecY8jhjPiGcrBCedrNK8eFIxuDYqGWRrUMOiMsnfy5HEO2L%2Fyeg1KPigJdQjx7w7IgoOWH4UOi02AlckEjAMrav%2Fy9Xq8R5uq4exD%2Bbl5nXUeRNkBZXmugJfb7wyGd6rLI4ER3HCdzfug%2BXOwubvGKZFARVYbRjazI0Jc1Y59bu76ZUaJwCDl%2FGEmIsuDTozodXewTcPiurLrSX7ZSBHOi9tHM1KUxnw%2F3K4dsGnZFIjLIlljjd162PYae6LEodemx9GSYYjOfkVqSlrLRpJ9YT4%2FZv7vE%2BD2ydyfMq7Yd%2FWzfrcjtx40EE%2FJpo4d3jXSPKT3KGOvxaygsk7xveNLvnl0%2FZeAkNJWOi7EZGv3J8yL8sB%2FaoUDND4HqiPbrywGvNcdYDSKlKzE5aT31SftewnDO4SZU%2F73Gocu0GJxH0uI%2Bnk5h9wQpuoXIhXTjiJED%2BOn38wbPhQcyEJk8GyI5Oq0aapkHCzhn3f%2BRtutDVEOWeb0HimtbzP5hM85ber8mi%2F6uNswb0%2Bqcv8v5OjvwYH5kyERdVb%2BgPK69DBCTXF39v1okUbh%2FArlxZkIMn2keembbnm0HD4onk46h%2F3pek6H8DzLAnMODM%2FwvsakuQcYwX%2Bi4qd9o30g1Ihc%2FVORXgp02%2F26FfWzYGreh7J%2FRJjI%2B4TrE%2FAVzx5%2B8XNxVOmbZ02VTvOBd4QkzyLFxn9dM4DsXwpEJiFAJ6L2%2F2NrnTEUTbNag1NPiZhfZ%2FlMZTsn2ieIaj58AuWfMJDSIbRoGe07WJdwM0IYXMoH7%2BZkPcLpHDJr9ykDagNLDOzgOb8oNHVqk3Ih5LDUH1ErlrVbuEVs2X317UHEE2ac4PIRp4cYqrnvRPmRllhmClWE1TtK7lJ38ZXRkrIruLOzjtv6y3yiLTo7BfZ7HNyN5cxvrMmAgPCojUZi%2BbLy8SxjsTM4eVHr1eS2bwZgS6zniDyFl1rZoWvEa6hNQIO6DrwQ8dnbXqftyFxKAC3pTtkAze1BbDJrAs%2BRSfKJsgoy9%2BCMdnLoUXBThaTdLeLJab1W4mlf6rOCll4eZSbX7bXFNZd%2FMENXnUXoPDdJAUYzUPzvTBa8fOlp1z%2FhEvB%2Fgq300apHTA%2FAzXyU14GX7YqRa8nj09PT%2FqJgQmdmOHzoUyjYmUq7k0UW03bx%2BGdK3tIyIVFqLgKEkAh%2BLW%2FbRf7WFqCeh01KPODPEmcNh4OHViF%2BWcSMM7FNBvDLRw8BP%2BTIlCJ2wGQyJBmDodzsA6hMcmRlmoJicmAI1phu0XbgQ5ZVwUoLGOB2MlvhrcvLno949hYRpsQF98JzXB%2BDogg7etnK2g3BwCqt%2Ba3onTcXYbQgsLiBVf%2FhlYQ4vZI1yE1c3iXEmh%2Fh4KBr%2FvvIrEUtZI0hGFzLz2TcCbPEC18Aa1apKlhwz8TV8TveVglDyRBfWIzRkDDCkoVURqMaCwXpmqP87N5129W8Tf%2BstUU02SmZrAvD7%2B3BJ5gc6kNtfIW4y3uWZXCJNt5bbksu0Hag6XutNGhFy1DDNyJUX%2FZ4E4%2Fyj8d7p4JCH6HkFJBAtsTIZJXvBMrXNXU203%2FH9PoiUWbcddRh44v2CA3IPZdlX1AsH3qVi8JbS2pMb08wVenI9UjsA0ruMmfMqjpWGnC4leCzYyLWZpSglavSYLMZMzh8MIMxB%2BQqLsr%2Bb6tB%2F7Kz9wDnYPFJfAoqbWwyRV9cE27Hx31%2Bl3FlJ6gex34vXh%2B2cipY2Lp8SeluZlsKVgtCOlfc3vjTmIMN1bJW2Z6bJi6vfW4ciZHMMH0oZeuSCPQavS%2BKvHWXzuA%3D&__ASYNCPOST=true&";

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
        System.out.println(response.body().string());

    }
    @Test
    void enumTest(){
        System.out.println(TwoClassCode.getValue("736_231"));
        System.out.println(TwoClassCode.getCode("前鎮區"));
    }

    @Test
    void reptileUtilTest() throws IOException {
        System.out.println(ReptileUtil.requesterGet("https://th.kerryexpress.com/en/track/?track=BANAZ00113574", true));
    }
    @Test
    void kerryOrderReptileTest() throws IOException {
        KerryOrderReptile kerryOrderReptile = new KerryOrderReptile();
        List<KerryOrderUpEntity> kerryOrderUpList = new ArrayList<>();
        kerryOrderUpList.add(KerryOrderUpEntity.builder().expNum("BANAZ00113574").build());
        kerryOrderUpList.add(KerryOrderUpEntity.builder().expNum("BANAZ00113700").build());
        kerryOrderUpList.add(KerryOrderUpEntity.builder().expNum("BANAZ00113467").build());
        kerryOrderUpList.add(KerryOrderUpEntity.builder().expNum("BANAZ00113731").build());
        kerryOrderUpList.add(KerryOrderUpEntity.builder().expNum("BANAZ00113544").build());
        System.out.println(kerryOrderReptile.getKerryOrderDownList(kerryOrderUpList));



    }
    @Test
    void reptileUtilPostTest() throws IOException {
        String result = ReptileUtil.requesterPost(
                "https://ecfme.famiport.com.tw/fmedcfpwebv2/index.aspx/GetOrderDetail",
                "application/json",
                "{\"EC_ORDER_NO\":\"191027211215\",\"RCV_USER_NAME\":null,\"ORDER_NO\":\"\"}"
        );

        result = result.substring(6, result.length() - 2).replace("\\","").toLowerCase();
        System.out.println(result);
        ObjectMapper objectMapper = new ObjectMapper();
        FamiportResponseResult famiportResponseResult = objectMapper.readValue(result, FamiportResponseResult.class);
        List list = famiportResponseResult.getList();

        String famiportResponseExpInfoJson = objectMapper.writeValueAsString(list.get(0));
        FamiportResponseExpInfo famiportResponseExpInfo = objectMapper.readValue(famiportResponseExpInfoJson,FamiportResponseExpInfo.class);
        System.out.println(famiportResponseExpInfo);
    }

    @Test
    void linkTest() throws IOException {

        for (int i = 2000; i <= 3000; i++) {
            String url = "http://shop10.dwdcadea.com/shop/?m=Item&a=show&id="+i;
            String result = ReptileUtil.requesterGet(url,false);
            Document document = Jsoup.parse(result);
            //selector
            //body > table > tbody > tr:nth-child(2) > td > div:nth-child(1) > div:nth-child(2)
            Element element = document.selectFirst("body > table > tbody > tr:nth-child(2) > td > div:nth-child(1) > div:nth-child(2)");
            if (element!=null){
                System.out.println(i+":"+element.text());
            }
        }
    }

    @Test
    void linkTest8() throws IOException {

        for (int i = 8000; i <= 9000; i++) {
            String url = "http://shop10.dwdcadea.com/shop/?m=Item&a=show&id="+i;
            String result = ReptileUtil.requesterGet(url,false);
            Document document = Jsoup.parse(result);
            //selector
            //body > table > tbody > tr:nth-child(2) > td > div:nth-child(1) > div:nth-child(2)
            Element element = document.selectFirst("body > table > tbody > tr:nth-child(2) > td > div:nth-child(1) > div:nth-child(2)");
            if (element!=null){
                System.out.println(i+":"+element.text());
            }
        }
    }
    @Test
    void linkTest9() throws IOException {

        for (int i = 9000; i <= 10000; i++) {
            String url = "http://shop10.dwdcadea.com/shop/?m=Item&a=show&id="+i;
            String result = ReptileUtil.requesterGet(url,false);
            Document document = Jsoup.parse(result);
            //selector
            //body > table > tbody > tr:nth-child(2) > td > div:nth-child(1) > div:nth-child(2)
            Element element = document.selectFirst("body > table > tbody > tr:nth-child(2) > td > div:nth-child(1) > div:nth-child(2)");
            if (element!=null){
                System.out.println(i+":"+element.text());
            }
        }
    }
}
