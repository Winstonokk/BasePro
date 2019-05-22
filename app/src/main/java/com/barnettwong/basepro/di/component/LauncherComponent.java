package com.barnettwong.basepro.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.barnettwong.basepro.di.module.LauncherModule;
import com.barnettwong.basepro.mvp.contract.LauncherContract;

import com.jess.arms.di.scope.ActivityScope;

import com.barnettwong.basepro.mvp.ui.activity.LauncherActivity;


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
@ActivityScope
@Component(modules = LauncherModule.class, dependencies = AppComponent.class)
public interface LauncherComponent {
    void inject(LauncherActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        LauncherComponent.Builder view(LauncherContract.View view);

        LauncherComponent.Builder appComponent(AppComponent appComponent);

        LauncherComponent build();
    }
}