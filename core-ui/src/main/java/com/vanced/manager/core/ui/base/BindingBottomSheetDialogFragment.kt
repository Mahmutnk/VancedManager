package com.vanced.manager.core.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BindingBottomSheetDialogFragment<VB : ViewBinding> : BottomSheetDialogFragment() {

    private var _binding: VB? = null
    protected val binding: VB get() = requireNotNull(_binding)

    override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
        _binding = binding(inflater, container, savedInstanceState)
        otherSetups()
        return binding.root
    }

    protected abstract fun binding(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): VB

    protected open fun otherSetups() = Unit

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}