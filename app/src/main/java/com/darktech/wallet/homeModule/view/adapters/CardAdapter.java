package com.darktech.wallet.homeModule.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.darktech.wallet.R;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private ArrayList<Card> cards;
    private Context context;

    public CardAdapter(ArrayList<Card> cards, Context context) {
        this.cards = cards;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        context = parent.getContext();
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card currentCar = cards.get(position);

        holder.tvBalance.setText(String.format("$%.2f", currentCar.getBalance().toString()));
        holder.tvNumber.setText(currentCar.getCardNumber());
        holder.tvValidThru.setText(currentCar.getExpirationDate());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {

        TextView tvBalance;
        TextView tvNumber;
        TextView tvValidThru;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBalance = itemView.findViewById(R.id.card_balance);
            tvNumber = itemView.findViewById(R.id.card_number);
            tvValidThru = itemView.findViewById(R.id.card_valid_thru);
        }
    }
}
