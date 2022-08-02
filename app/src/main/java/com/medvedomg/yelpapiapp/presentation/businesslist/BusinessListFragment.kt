package com.medvedomg.yelpapiapp.presentation.businesslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medvedomg.yelpapiapp.databinding.FragmentBusinessListBinding
import com.medvedomg.yelpapiapp.presentation.util.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class BusinessListFragment : Fragment() {

    private lateinit var binding: FragmentBusinessListBinding

    private val viewModel by viewModel<BusinessListViewModel>()

    private val adapter by lazy { BusinessListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusinessListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.recyclerView) {
            adapter = this@BusinessListFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.viewStateLiveData.observe(viewLifecycleOwner, ::setState)
    }

    private fun setState(state: ViewState<List<BusinessModel>>) {
        with(binding) {
            when (state) {
                is ViewState.Success -> {
                    loader.visibility = View.GONE
                    tvError.visibility = View.GONE
                    adapter.setData(state.data)
                    recyclerView.visibility = View.VISIBLE
                }
                is ViewState.Error -> {
                    loader.visibility = View.GONE
                    tvError.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    tvError.text = state.errorMessage
                }
                is ViewState.Loading -> {
                    binding.loader.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    adapter.setData(emptyList())
                }
            }
        }
    }
}