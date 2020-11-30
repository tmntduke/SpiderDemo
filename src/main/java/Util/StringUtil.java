package Util;

public class StringUtil {

    public static String ANSWER_URL="https://www.icourse163.org/dwr/call/plaincall/PostBean.getPaginationReplys.dwr";
    public static String COMMETN_URL="https://www.icourse163.org/dwr/call/plaincall/PostBean.getPaginationComments.dwr";

    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.67 Safari/537.36";
    public static final String COMMENT_BODY = "callCount=1\n" +
            "scriptSessionId=${scriptSessionId}190\n" +
            "httpSessionId=ec4d7e5d3a404a9a824a84a340170ab9\n" +
            "c0-scriptName=PostBean\n" +
            "c0-methodName=getPaginationComments\n" +
            "c0-id=0\n" +
            "batchId=1606533384898\n";
    public static final String ANSWER_BODY = "callCount=1\n" +
            "scriptSessionId=${scriptSessionId}190\n" +
            "httpSessionId=ec4d7e5d3a404a9a824a84a340170ab9\n" +
            "c0-scriptName=PostBean\n" +
            "c0-methodName=getPaginationReplys\n" +
            "c0-id=0\n" +
            "c0-param1=number:2\n" +
            "batchId=1606530107027\n";

    public static String COOKIE = "close_topBar=1; CLIENT_IP=124.64.19.66; EDUWEBDEVICE=a2b059d533f841b1b4901b92f4500c32; __yadk_uid=5lrX6UmLzvPqOPtG89A5CPydTRG5VbyG; WM_TID=sRN%2FNQblmltAQVRERFM7IPfNRB4BAaF7; WX_WEBVIEW_AUTH=1; STUDY_WTR=\"1ItDq/BvVKhsArewJdDsqVaH5qvpuhuCZJHsg8rOg7/dmpJxMQxdGN/hU5vFqcVviF6t65gjWkef69FZ1wiWd+ctFtLo2+dk1T5E80p7J2Q=\"; hb_MA-A976-948FFA05E931_source=cn.bing.com; MOOC_PRIVACY_INFO_APPROVED=true; close_topBar=1; hasVolume=true; videoVolume=0.8; Hm_lvt_77dc9a9d49448cf5e629e5bebaa5500b=1605020156,1606176597,1606446611,1606451517; NTESSTUDYSI=ec4d7e5d3a404a9a824a84a340170ab9; WM_NI=sge6m8%2FarniWCzVQxi7EeGPVKW%2FaCUtczLdWqb46k9WINf3xEhf8ejYdwAqoPDddj3PTLct2HJ2k%2BRrAcTnl2fxt%2FVHsvm9bElOqHLFB6oBvXvHmzruD1ETS%2FE26xwLBR3U%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eeb5d07285a88db7b35cb7b48ea2c84a878a9e84aa6a978997b7cf4892e7a584cc2af0fea7c3b92ab1b9abd7f034edbfbe8fec5d81a69ab2fb63f59baba8d35e8989e182c25fb8bdb6ade75db3befcd7ea3395978287b27487b8aad9e8629bbcb9a2f465949cac85e939948c8484f73a8c96fe8caa5ab29fadb5ed68f2bab895ae5e8cadba8ebc6a87b48192cb48869efa90c17e9caa888ef97ab79cffa6d759ed9bfed7fb7d909e9ed4d837e2a3; NTES_YD_SESS=Qk3vPMTAw_xrMS2E8JwQysadYQLJx8gRNMp8yj3n_pj0SnZrSDeaKRvaXEHV9IJMKjMa7g1GBRGgPEmFU5MXAnfC_my.PuYHguWnYLbjx5expT88d1vvsPrDLHXjGMFOqUIDb5Hkz.TRtOa3pFJfH1D3HzCYFfebz2qZgG2GrsMJ1VGVJ4zIRNG25jXziEYxbkdhZFBvcUJWVim.oiDxNv6Nry8HcYZl3AlGVpk9dckIh; NTES_YD_PASSPORT=oX2dCRonsrXTA7KTsDtIKqQumo5GJsPqoSvFz9nhk8.CbX4tbOxW2SsWLQiDgG1o2BoWhAm6TS6FBa8VV8wBsanANiW01hZHQIhyd_NsQKVYabO6yzwAY4RmV3e79cxHgYtFkNgDxhJFbbVJ63RgwOGz64Pb8OaIHXn4HnSaBfwKrCrDWi94uPB5dBgbHnxPCW3M_XVpd4K_.Qguggzp97tKL; S_INFO=1606486466|0|3&80##|18860894225; P_INFO=18860894225|1606486466|1|imooc|00&99|bej&1606272610&imooc#bej&null#10#0#0|&0|null|18860894225; STUDY_INFO=\"yd.28b8de3f1f244033b@163.com|8|1383643350|1606486466457\"; STUDY_SESS=\"CzxvbTFo8DonjSDCN5/iDeQBtweDnPT8wVHBSezn7kwoG/fHYJD4LRLRkhb5x4c+L/Uk+3LIttMj2QJFMWMDtjkAQN9R1CcNg0mMZuj6E6+agx3YCIt8YVCVHgpmUtdF90SWQLryT5IlfbwsnEdK5Spo8ih1WMG6ZZaUk+cYXNQLhur2Nm2wEb9HcEikV+3FTI8+lZKyHhiycNQo+g+/oA==\"; STUDY_PERSIST=\"vhYnKT3lx28THBLc7r/+csWG73E7EzA2G36Nrbl4AYv4kyOf5v3UnQE8hzZErA8b1xQfesBhf5wf0LsqclexKW+yZTAoyrKHpTwEKExzWq0KiA+ai0peBgYOAT5ssTb7HVLFuIR1lH6WbjvNSzEJslT+SoOC19324iM+I6d993mm4+h+uCOrJdLLSo1das+ic/JvJ9FeKsX8cLAeHJSrfmt5ctGILRHvv25hGF1I9WnZgpjCC7Iso4RP9U87vJE8LtaQzUT1ovP2MqtW5+L3Hw+PvH8+tZRDonbf7gEH7JU=\"; NETEASE_WDA_UID=1383643350#|#1546682898421; Hm_lpvt_77dc9a9d49448cf5e629e5bebaa5500b=1606487182";

    public static boolean isFirst=true;

}
