package com.goodHot.fun.service.impl;

import com.alibaba.fastjson.JSON;
import com.goodHot.fun.dto.rpc.IIILab;
import com.goodHot.fun.service.IIIlabService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.net.URLEncoder;

@Service
@Slf4j
public class IIIlabServiceImpl implements IIIlabService {

    private ScriptEngineManager manager = new ScriptEngineManager();

    @Override
    public IIILab douyin(String url) {
        return rpc(url, "douyin");
    }

    @Override
    public IIILab weibo(String url) {
        return rpc(url, "weibo");
    }

    private IIILab rpc(String link, String site) {
        String server = "https://service0.iiilab.com/video/web1/";
        String random = random();
        String sign = null;
        String xclientDate = null;
        String result = null;
        try {
            sign = sign(link + "@" + random);
            xclientDate = xclientDate(site, sign);
            String param = "link=" + URLEncoder.encode(link) + "&r=" + random + "&s=" + sign;
            String reqURL = server + site + "?" + param;
            result = httpCall(reqURL, xclientDate);
            log.info("调用iiilib结果【{}】", result);
        } catch (ScriptException | UnirestException e) {
            log.error("调用【{}】失败，地址【{}】，异常【{}】", "https://service0.iiilab.com/video/web1/", link, e.getMessage());
            return IIILab.fail();
        }
        if (result == null) {
            return IIILab.fail();
        }
        IIILab iiiLab = JSON.parseObject(result, IIILab.class);
        return iiiLab;
    }

    public String httpCall(String url, String xclientDate) throws UnirestException {
        HttpResponse<String> response = Unirest.post(url)
                .header("Accept", "application/json, text/javascript, */*; q=0.01")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-CN,zh;q=0.9")
                .header("Connection", "keep-alive")
//                .header("Content-Length", "74")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Cookie", "iii_Session=91pi0gvosfaqamgj8lr3sptbj3; _ga=GA1.2.1211182193.1545792266; _gid=GA1.2.439920918.1545792266; _gsp=GA3d5749de6751f74f; _gat=1; PHPSESSIID=311701951545")
                .header("Host", "service0.iiilab.com")
                .header("Origin", "https://douyin.iiilab.com")
                .header("Referer", "https://douyin.iiilab.com/")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36")
                .header("X-Client-Data", xclientDate)
                .header("cache-control", "no-cache")
                .body("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"link\"\r\n\r\nhttp://v.douyin.com/8sa4UK/\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"r\"\r\n\r\n7814128863081462\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"s\"\r\n\r\n2472085344\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--")
                .asString();
        /**
         * {
         *     "retCode": 200,
         *     "retDesc": "成功",
         *     "data": {
         *         "cover": "https://p1.pstatp.com/large/14f5d0005a774630df76d.jpg",
         *         "text": "洗澡的时候沾点水能要了他的命，捞鱼的时候倒是一点都不怕",
         *         "video": "http://v9-dy-y.ixigua.com/e667aa4f6245ad60117503be9e22ca6a/5c245436/video/m/220096a97a1830445f39842cb719900076c115fe86e0000afe2a09eefeb/?rc=ajwzaHQ2djZwajMzOmkzM0ApQHRAb0lIOTg0NTozNDQ4NDo1OzNAKXUpQGczdylAZmxkamV6aGhkZjs0QDFpXzVmajZzY18tLWMtMHNzLW8jbyM%2BNTItNC0uLS0uLy4vLi4vaTpiLW8jOmAtbyNtbCtiK2p0OiMvLl4%3D"
         *     },
         *     "succ": true
         * }
         */
        return response.getBody();
    }

    /**
     * 生成随机数
     *
     * @return
     */
    private String random() {
        return (Math.random() + "").substring(2);
    }

    /**
     * 获取签名
     *
     * @param i
     * @return
     * @throws ScriptException
     */
    private String sign(String i) throws ScriptException {
        String script = "var gen = function() {\n" +
                "  var t = '" + i + "';\n" +
                "  var a = function() {\n" +
                "      for (var t = 0, e = new Array(256), n = 0; 256 != n; ++n) t = 1 & (t = 1 & (t = 1 & (t = 1 & (t = 1 & (t = 1 & (t = 1 & (t = 1 & (t = n) ? -306674912 ^ t >>> 1 : t >>> 1) ? -306674912 ^ t >>> 1 : t >>> 1) ? -306674912 ^ t >>> 1 : t >>> 1) ? -306674912 ^ t >>> 1 : t >>> 1) ? -306674912 ^ t >>> 1 : t >>> 1) ? -306674912 ^ t >>> 1 : t >>> 1) ? -306674912 ^ t >>> 1 : t >>> 1) ? -306674912 ^ t >>> 1 : t >>> 1, e[n] = t;\n" +
                "      return \"undefined\" != typeof Int32Array ? new Int32Array(e) : e\n" +
                "    }();\n" +
                "  return function(t) {\n" +
                "    for (var e, n, i = -1, o = 0, r = t.length; o < r;) i = (e = t.charCodeAt(o++)) < 128 ? i >>> 8 ^ a[255 & (i ^ e)] : e < 2048 ? (i = i >>> 8 ^ a[255 & (i ^ (192 | e >> 6 & 31))]) >>> 8 ^ a[255 & (i ^ (128 | 63 & e))] : 55296 <= e && e < 57344 ? (e = 64 + (1023 & e), n = 1023 & t.charCodeAt(o++), (i = (i = (i = i >>> 8 ^ a[255 & (i ^ (240 | e >> 8 & 7))]) >>> 8 ^ a[255 & (i ^ (128 | e >> 2 & 63))]) >>> 8 ^ a[255 & (i ^ (128 | n >> 6 & 15 | (3 & e) << 4))]) >>> 8 ^ a[255 & (i ^ (128 | 63 & n))]) : (i = (i = i >>> 8 ^ a[255 & (i ^ (224 | e >> 12 & 15))]) >>> 8 ^ a[255 & (i ^ (128 | e >> 6 & 63))]) >>> 8 ^ a[255 & (i ^ (128 | 63 & e))];\n" +
                "    return -1 ^ i\n" +
                "  }(t) >>> 0\n" +
                "}();";
        ScriptEngine engine = manager.getEngineByName("js");
        engine.eval(script);
        Object gen = engine.get("gen");
        if (gen instanceof Double) {
            return ((Double) gen).longValue() + "";
        }
        return String.valueOf(gen);
    }

    /**
     * 获取X-Client-Date MD值
     *
     * @param site
     * @param t
     * @return
     * @throws ScriptException
     */
    private String xclientDate(String site, String t) throws ScriptException {
        String script = "var site = '" + site + "';\n" +
                "var t = '" + t + "';\n" +
                "var e = site.charAt(t.charCodeAt(0) % site.length);\n" +
                "var n = site.charAt(t.charCodeAt(t.length - 1) % site.length);";
        ScriptEngine engine = manager.getEngineByName("js");
        engine.eval(script);
        Object e = engine.get("e");
        Object n = engine.get("n");
        return md5(e + t + n, "", "");
    }

    /**
     * iiilab.com 的md5算法
     *
     * @param t
     * @param e
     * @param n
     * @return
     * @throws ScriptException
     */
    private String md5(String t, String e, String n) throws ScriptException {
        String script = "function h(t, e) {\n" +
                "    var n = (65535 & t) + (65535 & e);\n" +
                "    return (t >> 16) + (e >> 16) + (n >> 16) << 16 | 65535 & n\n" +
                "}\n" +
                "function s(t, e, n, i, o, r) {\n" +
                "    return h(function a(t, e) {\n" +
                "        return t << e | t >>> 32 - e\n" +
                "    }(h(h(e, t), h(i, r)), o), n)\n" +
                "}\n" +
                "function d(t, e, n, i, o, r, a) {\n" +
                "    return s(e & n | ~e & i, t, e, o, r, a)\n" +
                "}\n" +
                "function f(t, e, n, i, o, r, a) {\n" +
                "    return s(e & i | n & ~i, t, e, o, r, a)\n" +
                "}\n" +
                "function g(t, e, n, i, o, r, a) {\n" +
                "    return s(e ^ n ^ i, t, e, o, r, a)\n" +
                "}\n" +
                "function p(t, e, n, i, o, r, a) {\n" +
                "    return s(n ^ (e | ~i), t, e, o, r, a)\n" +
                "}\n" +
                "function u(t, e) {\n" +
                "    t[e >> 5] |= 128 << e % 32,\n" +
                "    t[14 + (e + 64 >>> 9 << 4)] = e;\n" +
                "    var n, i, o, r, a, s = 1732584193,\n" +
                "        u = -271733879,\n" +
                "        c = -1732584194,\n" +
                "        l = 271733878;\n" +
                "    for (n = 0; n < t.length; n += 16) u = p(u = p(u = p(u = p(u = g(u = g(u = g(u = g(u = f(u = f(u = f(u = f(u = d(u = d(u = d(u = d(o = u, c = d(r = c, l = d(a = l, s = d(i = s, u, c, l, t[n], 7, -680876936), u, c, t[n + 1], 12, -389564586), s, u, t[n + 2], 17, 606105819), l, s, t[n + 3], 22, -1044525330), c = d(c, l = d(l, s = d(s, u, c, l, t[n + 4], 7, -176418897), u, c, t[n + 5], 12, 1200080426), s, u, t[n + 6], 17, -1473231341), l, s, t[n + 7], 22, -45705983), c = d(c, l = d(l, s = d(s, u, c, l, t[n + 8], 7, 1770035416), u, c, t[n + 9], 12, -1958414417), s, u, t[n + 10], 17, -42063), l, s, t[n + 11], 22, -1990404162), c = d(c, l = d(l, s = d(s, u, c, l, t[n + 12], 7, 1804603682), u, c, t[n + 13], 12, -40341101), s, u, t[n + 14], 17, -1502002290), l, s, t[n + 15], 22, 1236535329), c = f(c, l = f(l, s = f(s, u, c, l, t[n + 1], 5, -165796510), u, c, t[n + 6], 9, -1069501632), s, u, t[n + 11], 14, 643717713), l, s, t[n], 20, -373897302), c = f(c, l = f(l, s = f(s, u, c, l, t[n + 5], 5, -701558691), u, c, t[n + 10], 9, 38016083), s, u, t[n + 15], 14, -660478335), l, s, t[n + 4], 20, -405537848), c = f(c, l = f(l, s = f(s, u, c, l, t[n + 9], 5, 568446438), u, c, t[n + 14], 9, -1019803690), s, u, t[n + 3], 14, -187363961), l, s, t[n + 8], 20, 1163531501), c = f(c, l = f(l, s = f(s, u, c, l, t[n + 13], 5, -1444681467), u, c, t[n + 2], 9, -51403784), s, u, t[n + 7], 14, 1735328473), l, s, t[n + 12], 20, -1926607734), c = g(c, l = g(l, s = g(s, u, c, l, t[n + 5], 4, -378558), u, c, t[n + 8], 11, -2022574463), s, u, t[n + 11], 16, 1839030562), l, s, t[n + 14], 23, -35309556), c = g(c, l = g(l, s = g(s, u, c, l, t[n + 1], 4, -1530992060), u, c, t[n + 4], 11, 1272893353), s, u, t[n + 7], 16, -155497632), l, s, t[n + 10], 23, -1094730640), c = g(c, l = g(l, s = g(s, u, c, l, t[n + 13], 4, 681279174), u, c, t[n], 11, -358537222), s, u, t[n + 3], 16, -722521979), l, s, t[n + 6], 23, 76029189), c = g(c, l = g(l, s = g(s, u, c, l, t[n + 9], 4, -640364487), u, c, t[n + 12], 11, -421815835), s, u, t[n + 15], 16, 530742520), l, s, t[n + 2], 23, -995338651), c = p(c, l = p(l, s = p(s, u, c, l, t[n], 6, -198630844), u, c, t[n + 7], 10, 1126891415), s, u, t[n + 14], 15, -1416354905), l, s, t[n + 5], 21, -57434055), c = p(c, l = p(l, s = p(s, u, c, l, t[n + 12], 6, 1700485571), u, c, t[n + 3], 10, -1894986606), s, u, t[n + 10], 15, -1051523), l, s, t[n + 1], 21, -2054922799), c = p(c, l = p(l, s = p(s, u, c, l, t[n + 8], 6, 1873313359), u, c, t[n + 15], 10, -30611744), s, u, t[n + 6], 15, -1560198380), l, s, t[n + 13], 21, 1309151649), c = p(c, l = p(l, s = p(s, u, c, l, t[n + 4], 6, -145523070), u, c, t[n + 11], 10, -1120210379), s, u, t[n + 2], 15, 718787259), l, s, t[n + 9], 21, -343485551),\n" +
                "    s = h(s, i),\n" +
                "    u = h(u, o),\n" +
                "    c = h(c, r),\n" +
                "    l = h(l, a);\n" +
                "    return [s, u, c, l]\n" +
                "}\n" +
                "function c(t) {\n" +
                "    var e, n = \"\",\n" +
                "        i = 32 * t.length;\n" +
                "    for (e = 0; e < i; e += 8) n += String.fromCharCode(t[e >> 5] >>> e % 32 & 255);\n" +
                "    return n\n" +
                "}\n" +
                "function l(t) {\n" +
                "    var e, n = [];\n" +
                "    for (n[(t.length >> 2) - 1] = void 0, e = 0; e < n.length; e += 1) n[e] = 0;\n" +
                "    var i = 8 * t.length;\n" +
                "    for (e = 0; e < i; e += 8) n[e >> 5] |= (255 & t.charCodeAt(e / 8)) << e % 32;\n" +
                "    return n\n" +
                "}\n" +
                "function r(t) {\n" +
                "    var e, n, i = \"\";\n" +
                "    for (n = 0; n < t.length; n += 1) e = t.charCodeAt(n),\n" +
                "    i += \"0123456789abcdef\".charAt(e >>> 2 & 15) + \"0123456789abcdef\".charAt(15 & e);\n" +
                "    return i\n" +
                "}\n" +
                "function n(t) {\n" +
                "    return unescape(encodeURIComponent(t))\n" +
                "}\n" +
                "function a(t) {\n" +
                "    return function e(t) {\n" +
                "        return c(u(l(t), 8 * t.length))\n" +
                "    }(n(t))\n" +
                "}\n" +
                "function m(t, e) {\n" +
                "    return function s(t, e) {\n" +
                "        var n, i, o = l(t),\n" +
                "            r = [],\n" +
                "            a = [];\n" +
                "        for (r[15] = a[15] = void 0, 16 < o.length && (o = u(o, 8 * t.length)), n = 0; n < 16; n += 1) r[n] = 909522486 ^ o[n],\n" +
                "        a[n] = 1549556828 ^ o[n];\n" +
                "        return i = u(r.concat(l(e)), 512 + 8 * e.length),\n" +
                "        c(u(a.concat(i), 640))\n" +
                "    }(n(t), n(e))\n" +
                "}\n" +
                "function e(t, e, n) {\n" +
                "    return e ? n ? m(e, t) : function i(t, e) {\n" +
                "        return r(m(t, e))\n" +
                "    }(e, t) : n ? a(t) : function o(t) {\n" +
                "        return r(a(t))\n" +
                "    }(t)\n" +
                "}\n" +
                "\n" +
                "var md5 = e('" + t + "', '" + e + "', '" + n + "');";
        ScriptEngine engine = manager.getEngineByName("js");
        engine.eval(script);
        Object md5 = engine.get("md5");
        return md5.toString();
    }


}
