package com.example.base.mvp;


public interface BaseMVP<V extends IView, P extends BasePresenter> {
    V createView();

    P createPresenter();
}
