package com.example.receta

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FavouritesAdapter(
    private val recipes: List<Recipe>,
    val onItemClicked: (Int) -> Unit
) :
    RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.title)
        val description = view.findViewById<TextView>(R.id.description)
        val image = view.findViewById<ImageView>(R.id.image)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouritesAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.favouirte_listitem, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = recipes.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.title.text = recipe.name
        holder.description.text = recipe.ingredients.toString()

        Glide.with(holder.view.context)
            .load(recipe.image)
            .into(holder.image)

    }
}