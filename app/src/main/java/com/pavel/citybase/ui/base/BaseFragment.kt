package com.pavel.citybase.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelProviders
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class BaseFragment<out V : ViewModel> : Fragment() {
  protected abstract val cityListViewModel: V
}

inline fun <reified V : ViewModel> BaseFragment<V>.factoryViewModel(noinline factory: ViewModelFactory<V>) =
  ViewModelDelegate(V::class.java, factory)

class ViewModelDelegate<V : ViewModel>(
  private val clazz: Class<V>,
  private val factory: ViewModelFactory<V>
) : ReadOnlyProperty<BaseFragment<V>, V> {
  override fun getValue(thisRef: BaseFragment<V>, property: KProperty<*>): V =
    ViewModelProviders.of(
      thisRef,
      object : Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = factory() as T
      }).get(clazz)
}

typealias ViewModelFactory<V> = () -> V
