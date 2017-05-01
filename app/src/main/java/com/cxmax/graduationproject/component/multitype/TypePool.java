package com.cxmax.graduationproject.component.multitype;

import android.support.annotation.NonNull;

/**
 * @describe : control{@link AbsItemProvider} in {@link MultiTypePool}
 *             through add()  , remove() , replace() : after consideration, i decide to realize it in add()
 * @usage :
 * <p>
 * <p>
 * Created by caixi on 2017/3/26.
 */

public interface TypePool<T> {

    /**
     * if you don't specify an item view type when you add a new {@link AbsItemProvider}, it will be automatic.
     * for better source code management,i strong recommend to use a specific view type when provider is added
     *
     * @param provider
     */
    @Deprecated
    void add(@NonNull AbsItemProvider provider);

    /**
     * specify a view type , when {@link AbsItemProvider} be added
     * you can define an specific view type in {@link com.cxmax.graduationproject.app.ViewTypeConstant}
     *
     * @param viewType
     * @param provider
     */
    void add(int viewType, @NonNull AbsItemProvider provider);

    /**
     * replace();
     * @param viewType
     * @param allowReplace
     * @param provider
     */
    void add(int viewType, boolean allowReplace, @NonNull AbsItemProvider provider);

    /**
     * remove a specific {@link AbsItemProvider} from {@link MultiTypePool}
     *
     * @param provider
     */
    void remove(@NonNull AbsItemProvider provider);

    /**
     * remove a specific {@link AbsItemProvider} from {@link MultiTypePool} via view type param
     *
     * @param viewType
     */
    void remove(int viewType);

    /**
     * the result will be used in {@link android.support.v7.widget.RecyclerView.Adapter}
     *
     * @param item
     * @param position
     * @return
     */
    int getItemViewType(@NonNull T item, int position);

    int getItemViewType(@NonNull AbsItemProvider provider);

    AbsItemProvider getItemViewProvider(T item, int position);

    AbsItemProvider getItemViewProvider(int viewType);
}
