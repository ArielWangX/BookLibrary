/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.BookLibrary.ui.reviews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raywenderlich.android.BookLibrary.R
import com.raywenderlich.android.BookLibrary.model.relations.BookReview
import kotlinx.android.synthetic.main.item_book_review.view.*

class BookReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun showData(
        bookReview: BookReview,
        onItemSelected: (BookReview) -> Unit,
        onItemLongTapped: (BookReview) -> Unit
    ) = with(itemView) {
        val (review, book) = bookReview

        reviewTitle.text = context.getString(R.string.review_title, book.name)
        reviewRating.rating = review.rating.toFloat()
        reviewNumberOfComments.text =
            context.getString(R.string.number_of_reading_entries, review.entries.size)
        reviewDescription.text = review.notes
        Glide.with(this).load(review.imageUrl).into(bookImage)

        setOnClickListener { onItemSelected(bookReview) }
        setOnLongClickListener {
            onItemLongTapped(bookReview)
            true
        }
    }
}