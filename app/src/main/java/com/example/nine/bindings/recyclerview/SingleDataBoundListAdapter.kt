package com.example.nine.bindings.recyclerview

import androidx.annotation.LayoutRes
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil

/**
 * A RecyclerView [ListAdapter] for lists with items sharing the same single layout.
 *
 * Usage:
 *
 * 1. Define a ViewModel containing a list of items
 * ```
 * class AccountListViewModel : ViewModel() {
 *     class AccountItem(val id: Int, val balanceText: String)
 *
 *     val accounts = MutableLiveData<List<AccountItem>>()
 * }
 * ```
 *
 * 2. Provide a [DiffUtil.ItemCallback] for comparing list items (for example,
 * by exposing it as the ViewModel property).
 * ```
 * val accountDiff = object: DiffUtil.ItemCallback<AccountItem>() {
 *     override fun areItemsTheSame(oldItem: AccountItem, newItem: AccountItem) = oldItem.id == newItem.id
 *     override fun areContentsTheSame(oldItem: AccountItem, newItem: AccountItem) = oldItem.balanceText == newItem.balanceText
 * }
 * ```
 *
 * 3. Design the item layout. The layout should use the databinding <variable> called `item`.
 * ```
 * <data>
 *     <variable name="item" type="...AccountItem"
 *     ...
 *     <TextView
 *         android:text="@{item.id}"
 *         ...
 *     <TextView
 *         android:text="@{item.balanceText}"
 *         ...
 * </data>
 * ```
 *
 * 4. Associate an instance of [SingleDataBoundListAdapter] with [RecyclerView]. This can be done either in code:
 * ```
 * recyclerView.adapter = SingleDataBoundListAdapter(viewModel.accounts, R.layout.account_item, viewModel.accountDiff)
 * ```
 *
 * or in XML:
 * ```
 * <androidx.recyclerview.widget.RecyclerView
 *     ...
 *     app:items="@{viewModel.accounts}"
 *     app:itemLayout="@{@layout/account_item}"
 *     app:itemDiff="@{viewModel.accountDiff}" />
 * ```
 *
 * Created by Alex Tchivilev on 2019-07-30.
 *
 */
class SingleDataBoundListAdapter<T>(
        items: LiveData<List<T>>,
        @LayoutRes private val itemLayout: Int,
        itemDiff: DiffUtil.ItemCallback<T>
) : DataBoundListAdapter<T>(items, itemDiff) {

    @LayoutRes
    override fun getItemLayoutId(position: Int): Int = itemLayout
}