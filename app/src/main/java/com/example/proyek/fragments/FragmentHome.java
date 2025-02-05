package com.example.proyek.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyek.AfterClickMenu;
import com.example.proyek.R;
import com.example.proyek.RvItem;
import com.example.proyek.RvItem2;
import com.example.proyek.adapter.ListAdapterItem;
import com.example.proyek.adapter.ListAdapterItem2;
import com.example.proyek.adapter.ProductAdapter;
import com.example.proyek.adapter.RvAdapterUser;
import com.example.proyek.models.product.ProductModel;
import com.example.proyek.settergetter.RvItemSetGet;
import com.example.proyek.settergetter.RvItemSetGet2;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    private View view;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private RecyclerView rvNewProduct, rvPacket;
    private ProductAdapter productAdapter;
    private ArrayList<ProductModel> productList = new ArrayList<>();

    private SearchView searchView;
    private RecyclerView rvListMenu, rvListMenu2;

    private ArrayList<RvItemSetGet> list = new ArrayList<>();
    private ArrayList<RvItemSetGet2> list2 = new ArrayList<>();

    private RecyclerView.LayoutManager rvLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        rvNewProduct = view.findViewById(R.id.rvNewProduct);
        rvNewProduct.setHasFixedSize(true);


        // Custom Hint Color in Searchview
        TextView searchHint = searchView.findViewById(R.id.svHome);
        searchHint.setHint("Hari ini kamu mau beli apa?");
        searchHint.setHintTextColor(Color.BLACK);

        rvPacket = view.findViewById(R.id.rvPacket);

        showNewProductRV();
        showPacketRV();


        return view;
    }

    public void showNewProductRV() {
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvNewProduct.setLayoutManager(horizontalLayoutManager);

        rvNewProduct.setItemAnimator(new DefaultItemAnimator());

        FirebaseRecyclerOptions<ProductModel> options = new FirebaseRecyclerOptions.Builder<ProductModel>().setLifecycleOwner(getActivity())
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Data"), ProductModel.class).build();

        productAdapter = new ProductAdapter(options);

        rvNewProduct.setAdapter(productAdapter);
//        listAdapterItem.setOnItemClickCallback(new ListAdapterItem.OnItemClickCallback() {
//            @Override
//            public void onItemClicked(RvItemSetGet data) {
//                Intent i = new Intent(getActivity(), AfterClickMenu.class);
//                i.putExtra("rv_item", data);
////                i.putExtra("judul", data.getName());
//                startActivity(i);
//            }
//
//        });
    }

    private void showPacketRV() {
        list2.addAll(RvItem2.getListData());

        rvPacket.setLayoutManager(new LinearLayoutManager((getActivity())));
        rvLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvPacket.setLayoutManager(rvLayoutManager);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvPacket.setLayoutManager(horizontalLayoutManager);
        ListAdapterItem2 listAdapterItem = new ListAdapterItem2(list2);
        rvPacket.setAdapter(listAdapterItem);

        listAdapterItem.setOnItemClickCallback(new ListAdapterItem2.OnItemClickCallback() {
            @Override
            public void onItemClicked(RvItemSetGet2 data) {
                Intent i = new Intent(getActivity(), AfterClickMenu.class);
                i.putExtra("rv_item", data);
                startActivity(i);
            }

        });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        productAdapter.startListening();
//    }
}
