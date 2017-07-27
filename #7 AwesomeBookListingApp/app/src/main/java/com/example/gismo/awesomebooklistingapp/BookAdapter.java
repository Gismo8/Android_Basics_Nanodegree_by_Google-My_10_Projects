package com.example.gismo.awesomebooklistingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gismo on 7/13/2017.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> bookList;
    private Context context;

    public BookAdapter(List<Book> bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_book, parent, false);

        final BookAdapter.ViewHolder viewHolder = new BookAdapter.ViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookInfoLink = bookList.get(viewHolder.getAdapterPosition()).getInfoLink();
                Uri bookInfoUri;
                bookInfoUri = Uri.parse(bookInfoLink);
                Intent intent = new Intent(Intent.ACTION_VIEW, bookInfoUri);
                if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
                    v.getContext().startActivity(intent);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final BookAdapter.ViewHolder viewHolder, int position) {
        if (bookList == null) {
            return;
        }
        Book book = bookList.get(position);

        viewHolder.titleTextView.setText(book.getTitle());

        GlideApp.with(context).load(book.getImageUrl()).into(viewHolder.smallImage);

        List<String> authorsList = book.getAuthors();
        StringBuilder authors = new StringBuilder();
        if (authorsList.isEmpty()) {
            authors.append("No author");
        } else {
            authors.append(authorsList.get(0));
            for (int i = 1; i < authorsList.size(); i++) {
                authors.append(", ").append(authorsList.get(i));
            }
        }
        viewHolder.authorsTextView.setText(authors);
    }

    @Override
    public int getItemCount() {
        if (bookList == null) {
            return 0;
        }
        return bookList.size();
    }

    void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView authorsTextView;
        ImageView smallImage;

        ViewHolder(View itemView) {
            super(itemView);
            this.titleTextView = (TextView) itemView.findViewById(R.id.book_title);
            this.authorsTextView = (TextView) itemView.findViewById(R.id.book_authors);
            this.smallImage = (ImageView) itemView.findViewById(R.id.book_cover);
        }
    }
}
