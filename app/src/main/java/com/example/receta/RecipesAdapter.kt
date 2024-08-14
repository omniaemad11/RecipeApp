package com.example.receta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipesAdapter(
    private val recipes: List<Recipe>,
    val onItemClicked: (Int) -> Unit
) :
    RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.title)
        val description = view.findViewById<TextView>(R.id.description)
        val image = view.findViewById<ImageView>(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recipes_list_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.title.text = recipe.name
        holder.description.text = recipe.ingredients.toString()

        Glide.with(holder.view.context)
            .load(recipe.image)
            .into(holder.image)

        holder.view.setOnClickListener() {
            onItemClicked(recipe.id ?: 0)


        }
    }

    override fun getItemCount(): Int = recipes.size

}
