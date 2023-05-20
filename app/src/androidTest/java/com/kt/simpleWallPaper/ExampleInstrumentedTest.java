package com.kt.simpleWallPaper;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.kt.simpleWallPaper.api.NCallBack;
import com.kt.simpleWallPaper.api.Sll.SllNetWorkBusiness;
import com.kt.simpleWallPaper.api.Sll.base.SllBase;
import com.kt.simpleWallPaper.api.Unsplash.UnsplashNetWorkBusiness;
import com.kt.simpleWallPaper.api.bing.base.BingBase;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.kt.simpleWallPaper", appContext.getPackageName());
    }

    @Test
    public void test(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        UnsplashNetWorkBusiness unsplashNetWorkBusiness = new UnsplashNetWorkBusiness();

        unsplashNetWorkBusiness.getUnsplashData(1, "holidays", new NCallBack<BingBase>(appContext) {
            @Override
            protected void onResponse(BingBase response) {
                Log.i("TAG", "onResponse: "+response.getCode());
            }

            @Override
            public void onNoNetwork() {

            }
        });

//        assertEquals("com.kt.simpleWallPaper", appContext.getPackageName());
    }

}