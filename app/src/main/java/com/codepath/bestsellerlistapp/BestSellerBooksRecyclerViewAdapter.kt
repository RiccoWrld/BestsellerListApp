package com.codepath.bestsellerlistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.bestsellerlistapp.R.id

/**
 * [RecyclerView.Adapter] that can display a [BestSellerBook] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class BestSellerBooksRecyclerViewAdapter(
    private val books: List<BestSellerBook>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<BestSellerBooksRecyclerViewAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_best_seller_book, parent, false)
        return BookViewHolder(view)
    }

    inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: BestSellerBook? = null
        val mBookTitle: TextView = mView.findViewById(R.id.book_title)
        val mBookAuthor: TextView = mView.findViewById(R.id.book_author)
        val mRanking: TextView = mView.findViewById(R.id.ranking)
        val mBookImage: ImageView = mView.findViewById(R.id.book_image)
        val mBookDescription: TextView = mView.findViewById(id.book_description)
        val mBuyButton: Button = mView.findViewById(R.id.buy_button)

        override fun toString(): String {
            return "${mBookTitle.text} by ${mBookAuthor.text}"
        }
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        holder.mItem = book
        holder.mBookTitle.text = book.title
        holder.mBookAuthor.text = book.author
        holder.mRanking.text = book.rank.toString()
        holder.mBookDescription.text = book.description

        // Optional: Load image using Glide or Picasso
        // Glide.with(holder.mView.context).load(book.imageUrl).into(holder.mBookImage)
        Glide.with(holder.mView)
            .load(book.bookImageUrl)
            .centerInside()
            .into(holder.mBookImage)

        holder.mView.setOnClickListener {
            holder.mItem?.let { book -> mListener?.onItemClick(book) }

        }
    }

    override fun getItemCount(): Int {
        return books.size
    }
}
