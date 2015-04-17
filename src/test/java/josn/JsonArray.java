package josn;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str="[{'fid':'2pimJAEfEADgAFAkrBABIxO33n8=','fnumber':'李政','fname':'李政','ftype':20},{'fid':'2pimJAEfEADgAFBvrBABIxO33n8=','fnumber':'蔡兴','fname':'蔡兴','ftype':20},{'fid':'t6laugEfEADgALHJrBABIxO33n8=','fnumber':'马朝晖','fname':'马朝晖','ftype':20},{'fid':'1Au5DAEfEADgADmQrBABIxO33n8=','fnumber':'潘仙林','fname':'潘仙林','ftype':20},{'fid':'1Au5DAEfEADgADsbrBABIxO33n8=','fnumber':'周法宝','fname':'周法宝','ftype':20},{'fid':'1Au5DAEfEADgAD1RrBABIxO33n8=','fnumber':'邱菊芳','fname':'邱菊芳','ftype':20},{'fid':'1Au5DAEfEADgAFhFrBABIxO33n8=','fnumber':'蒋松羊','fname':'蒋松羊','ftype':20},{'fid':'1Au5DAEfEADgAFvnrBABIxO33n8=','fnumber':'陶蓉','fname':'陶蓉','ftype':20},{'fid':'2pimJAEfEADgACkPrBABIxO33n8=','fnumber':'陈方春','fname':'陈方春','ftype':20},{'fid':'2pimJAEfEADgAClRrBABIxO33n8=','fnumber':'缪军雄','fname':'缪军雄','ftype':20},{'fid':'2pimJAEfEADgACnrrBABIxO33n8=','fnumber':'陶国栋','fname':'陶国栋','ftype':20},{'fid':'2pimJAEfEADgACoSrBABIxO33n8=','fnumber':'程功','fname':'程功','ftype':20},{'fid':'2pimJAEfEADgACp5rBABIxO33n8=','fnumber':'周海军','fname':'周海军','ftype':20}]";
		str="[]";
		JSONArray jsonArray = JSONArray.fromObject(str);
		System.out.println(jsonArray);
		
		 for (int i = 0; i < jsonArray.size(); i++) {  
             JSONObject everyJsonObject=jsonArray.getJSONObject(i);  
             System.out.println(everyJsonObject.get("fid"));

             System.out.println("=====================================================");  
         }  
	}

}
