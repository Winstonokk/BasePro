package com.xjp.wonderfulcinema.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.xjp.wonderfulcinema.di.module.MainModule;

import com.jess.arms.di.scope.ActivityScope;
import com.xjp.wonderfulcinema.mvp.ui.activity.MainActivity;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}