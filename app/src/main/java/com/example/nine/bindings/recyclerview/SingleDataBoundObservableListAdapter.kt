package com.example.nine.bindings.recyclerview

import androidx.annotation.LayoutRes
import androidx.databinding.ObservableList

/**
 * A RecyclerView [Adapter] for lists with items sharing the same single layout.
 *
 * Usage:
 *
 * 1. Define a ViewModel containing a list of items
 * ```
 * class AccountListViewModel : ViewModel() {
 *     class AccountItem(val id: Int, val balanceText: String)
 *
 *     val accounts = ObservableArrayList<AccountItem>()
 * }
 * ```
 *
 * 2. Optionally, provide a function for extracting item's stable ID (for example,
 * by exposing it as the ViewModel property).
 * ```
 * val accountIdProvider = { account: AccountItem -> account.id }
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
 * 4. Associate an instance of [SingleDataBoundObservableListAdapter] with [RecyclerView]. This can be done either in code:
 * ```
 * recyclerView.adapter = SingleDataBoundObservableListAdapter(viewModel.accounts, R.layout.account_item, viewModel.accountIdProvider)
 * ```
 *
 * or in XML:
 * ```
 * <androidx.recyclerview.widget.RecyclerView
 *     ...
 *     app:items="@{viewModel.accounts}"
 *     app:itemLayout="@{@layout/account_item}"
 *     app:itemIdProvider="@{viewModel.accountIdProvider}" />
 * ```
 *
 * Created by Alex Chiviliov on 2019-08-05.
 *
 */
class SingleDataBoundObservableListAdapter<T>(
        items: ObservableList<T>,
        @LayoutRes private val layoutId: Int,
        itemIdProvider: ((T) -> Long)? = null
) : DataBoundObservableListAdapter<T>(items, itemIdProvider) {

    @LayoutRes
    override fun getItemLayoutId(position: Int): Int = layoutId
}