package com.barnettwong.basepro.di.module;

import dagger.Binds;
import dagger.Module;

import com.barnettwong.basepro.mvp.contract.LauncherContract;
import com.barnettwong.basepro.mvp.model.LauncherModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/20/2019 16:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class LauncherModule {

    @Binds
    abstract LauncherContract.Model bindLauncherModel(LauncherModel model);
}