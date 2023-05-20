package com.kt.simpleWallPaper;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kt.simpleWallPaper.api.download.DownloadNetWorkBusiness;
import com.kt.simpleWallPaper.api.My.MyNetWorkBusiness;
import com.kt.simpleWallPaper.api.My.base.TypeBase;
import com.kt.simpleWallPaper.api.My.base.UpDateBase;
import com.kt.simpleWallPaper.api.One.OneNetWorkBusiness;
import com.kt.simpleWallPaper.api.One.base.OneContentBase;
import com.kt.simpleWallPaper.api.Phone.PhoneNetWorkBusiness;
import com.kt.simpleWallPaper.api.Phone.base.resDataInfo.verticalList;
import com.kt.simpleWallPaper.api.Phone.base.resDataType.CategoryList;
import com.kt.simpleWallPaper.api.Sll.SllNetWorkBusiness;
import com.kt.simpleWallPaper.api.Sll.base.SllDataBase;
import com.kt.simpleWallPaper.api.Unsplash.UnsplashNetWorkBusiness;
import com.kt.simpleWallPaper.api.Unsplash.base.UnsplashDataBase;
import com.kt.simpleWallPaper.api.Wallhaven.WallhavenNetWorkBusiness;
import com.kt.simpleWallPaper.api.Wallhaven.base.WallhavenDataBase;
import com.kt.simpleWallPaper.api.bing.BingNetWorkBusiness;
import com.kt.simpleWallPaper.api.bing.base.BingDataBase;
import com.tencent.mmkv.MMKV;

import java.util.List;

public class Config {


    public static MMKV mmkv;

    // 路径
    public static final String PATH = "KT_network/wallpaper";

    // 全局网络请求
    public static MyNetWorkBusiness myNetWorkBusiness;
    public static PhoneNetWorkBusiness phoneNetWorkBusiness;
    public static SllNetWorkBusiness sllNetWorkBusiness;
    public static OneNetWorkBusiness oneNetWorkBusiness;
    public static BingNetWorkBusiness bingNetWorkBusiness;
    public static UnsplashNetWorkBusiness unsplashNetWorkBusiness;
    public static WallhavenNetWorkBusiness wallhavenNetWorkBusiness;
    public static DownloadNetWorkBusiness downloadNetWorkBusiness;


    // 软件内部版本号
    public static final double versionsNUM = 4.0;

    // 更新数据
    public static UpDateBase UPDATE;


    // 更新弹窗
    public static AlertDialog Udialog;
    public static View Uview;
    public static TextView Utitle, Utext,UconfirmText,UcancelText;
    public static LinearLayout Ucancel, Uconfirm,Ulinear;
    // 取消
    public static View.OnClickListener UOnCancelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Config.Udialog.dismiss();
        }
    };

    // 弹窗类型
    public static int Utype;
    // 确认
    public static View.OnClickListener UOnConfirmClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Uri uri = Uri.parse("https://support.qq.com/product/363974");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);

//            mSetIncidentListener.setDataIncidentListener();
        }
    };

    public interface onIncidentListener{
        void setDataIncidentListener();
    }
    public static onIncidentListener mSetIncidentListener;
    public static void setOnIncidentListener(onIncidentListener listener){
        mSetIncidentListener = listener;
    }


    // two页面的数据内容
    public static String TwoDataTag = "sll";


    // phone 分类数据
    public static List<CategoryList> PhoneTypeData;
    // phone 单个分类数据
    public static CategoryList PhoneTypeItem;

    // phone 数据列表
    public static List<verticalList> PhonePicInfo;
    // 单个数据
    public static verticalList PhoneInfoItem;
    // 页数
    public static int PhonePage;


    // 360 分类数据
    public static List<TypeBase> SllTypeData;
    // 360 单个分类数据
    public static TypeBase SllTypeItem;
    // 360 数据列表
    public static List<SllDataBase> SllPicInfo;
    // 360 页数
    public static int SllPage;


    // 测试页面的数据
    public static List<TypeBase> TTypeData;

    /*
    *
    * */

    // One 数据列表
    public static List<OneContentBase> OnePicPicInfo;
    // One 单个数据item
    public static OneContentBase OneInfoItem;
    // 时间
    public static long date;


    // Bing数据列表
    public static List<BingDataBase> BingPicInfo;
    // 页数
    public static int BingPage;

    // Unsplash 分类数据
    public static List<TypeBase> UnsplashTypeData;
    // Unsplash 单个分类数据
    public static TypeBase UnsplashTypeItem;
    // Unsplash 数据列表
    public static List<UnsplashDataBase> UnsplashPicInfo;
    // Unsplash 页数
    public static int UnsplashPage;

    // Wallhaven 分类数据
    public static List<TypeBase> WallhavenTypeData;
    // Wallhaven 单个分类数据
    public static TypeBase WallhavenTypeItem;
    // Wallhaven 数据列表
    public static List<WallhavenDataBase> WallhavenPicInfo;
    // Wallhaven 页数
    public static int WallhavenPage;



    // 是从那个特面进入的Look界面
    public static int PageType;
    // 是从第几个
    public static int numPicInfo;


    public static int PAGEINFOSIGN;
    public static int PAGETYPESIGN;
    public static int OPT;


}
