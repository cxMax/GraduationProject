## MultiTypeAdapter

### 介绍
主要是为了简化具有多个ItemType的RecyclerView.Adapter的构建代码,不用在Adapter里面搞来搞去,导致整个Adapter很臃肿.

### 使用方式
跟Popup一样,这个的使用也只需要关心五个步骤:
1. 创建<b>JavaBean类</b> :  ViewHolder的数据源
2. 创建<b>ViewHolder类</b> :   
                        a.在constructor里面初始化view;   
                        b.在update()方法里面,实现view更新;   
                        c.可以继承BaseVH  
3. 创建<b>Provider类</b> :   
                        <b>需继承AbsItemProvider</b>, AbsItemProvider具有于RecyclerView.Adapter相同的生命周期函数  
                        主要目的是实现ViewHolder与javabean的绑定.   
                        一般来说, 只需要override,onCreateViewHolder()和onBindViewHolder()即可,其他生命周期函数不用关心  
                        当然你还需要override,isForViewType(),这个是来判定provider持有的data是同一个viewtype的 
4. 创建<b>Adapter类</b> :  
                        <b>需继承MultiTypeAdapter</b>,
                        一般来说,只需要override,registerAllProvider()和updateData(), 
                        ItemViewType可以在<b>ViewTypeConstant</b>中定义  
                        当然,对应具体的业务或者参数需要传递到viewholder,可以重写onCreateViewHolder()或onBindViewHolder(),具体参考GameCouponPinnedAdapter
5. 最后在Activity或者Fragment中调用RecyclerView.setAdapter即可.

### 关于ReyclerView常用到的header/footer以及loadmore,refresh,pinned header等
1. 考虑到MultiTypeAdapter主要是为了简化多个ItemType构建,额外的header和footer可以通过Decorator来辅助实现,而不需要在现有的MultiTypeAdapter中进行增加ViewType
2. 以上的功能均可以通过一个单独的component来实现,具体参考MzRecyclerView

### 类关系
MultiTypeAdapter :　一个Adapter会持有一个MultiTypePool,对拥有的children进行注册．  
MultiTypePool　：　将不同ViewType的Provider保存在一个SparseArrayCompat里面,在对应Adapter的生命周期里面调用create和bind等相关方法.   
AbsItemProvider : Adapter和ViewHolder的桥梁, 涉及到一些具体业务上的参数或者接口调用,都可以通过此来中转.  
ViewTypeConstant : 保存不同Adapter的ViewType,每一个Adapter都可以其中新增一个child.  
ViewHolder : 实现具体的item相关的视图改变,以及数据绑定.   