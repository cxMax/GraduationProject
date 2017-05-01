package com.cxmax.graduationproject.base;

/**
 * @describe : the presenter hierarchy in MVP Architecture
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
