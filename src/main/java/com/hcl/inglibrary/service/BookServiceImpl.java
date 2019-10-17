package com.hcl.inglibrary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.inglibrary.dto.BookListByUserResponseDto;
import com.hcl.inglibrary.dto.BookListResponseDto;
import com.hcl.inglibrary.dto.RequestReserveDto;
import com.hcl.inglibrary.dto.ResponseReserveDto;
import com.hcl.inglibrary.repository.BookIssuedHistoryRepository;
import com.hcl.inglibrary.repository.BookRepository;

import com.hcl.inglibrary.dto.BookRequestDto;
import com.hcl.inglibrary.dto.DonateBookResponseDto;
import com.hcl.inglibrary.entity.Book;
import com.hcl.inglibrary.exception.BooksNotFoundException;
import com.hcl.inglibrary.util.ApplicationUtil;
import com.hcl.inglibrary.util.ExceptionConstants;
/**
 * 
 * @author Manisha Yadav
 * @apiNote This class is used to get/save the books from/to our library management system. 
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookIssuedHistoryRepository bookIssuedHistoryRepository;

	private static final String BOOKED = "issued";

	
	/*
	 * @Param -no param
	 * @Response -list of books
	 * @Exception -Books not found
	 * @Description -This method is used to fetch all the list of books which is available in the library.
	 * */
	@Override
	public List<BookListResponseDto> fetchBooks() {
		List<BookListResponseDto> bookListResponseDto = new ArrayList<>();
		List<Book> books = bookRepository.findAll();
		if(books != null) {
			books.forEach(book->{
				BookListResponseDto bookResponseDto = new BookListResponseDto();
				BeanUtils.copyProperties(book, bookResponseDto);
				bookListResponseDto.add(bookResponseDto);
			});
			return bookListResponseDto;
		}else {
			throw new BooksNotFoundException(ExceptionConstants.booksNotFound);
		}
	}
		
	/*
	 * @Param -bookRequestDto
	 * @Response -donateBookResponseDto
	 * @Exception -Books not found
	 * @Description -This method is used to save the book details which is donated by the specific user.
	 * */
	@Override
	public DonateBookResponseDto donateBook(BookRequestDto bookRequestDto) {

		Book book= new Book();
		book.setAuthorName(bookRequestDto.getAuthorName());
		book.setBookName(bookRequestDto.getBookName());
		book.setStatus(ApplicationUtil.defaultBookStatus);
		bookRepository.save(book);
		
		DonateBookResponseDto donateBookResponseDto = new DonateBookResponseDto();
		donateBookResponseDto.setMessage(ApplicationUtil.donateBookResponseDtoMsg);
		donateBookResponseDto.setStatusCode(200);
		return donateBookResponseDto;
	}

	/*
	 * @Param -userId
	 * @Response -list of books
	 * @Exception -Books not found
	 * @Description -This method is used to fetch the book details which is issued to the specific user.
	 * */
	@Override
	public List<BookListByUserResponseDto> fetchBooksByUser(Integer userId) {
		List<BookListByUserResponseDto> BookListByUserResponseDto = new ArrayList<>();
		List<Book> books = bookRepository.findByUserId(userId);
		if(books != null) {
			books.forEach(book->{
				BookListByUserResponseDto bookByUserResponseDto = new BookListByUserResponseDto();
				BeanUtils.copyProperties(book, bookByUserResponseDto);
				BookListByUserResponseDto.add(bookByUserResponseDto);
			});
			return BookListByUserResponseDto;
		}else {
			throw new BooksNotFoundException(ExceptionConstants.booksNotFound);
		}
	}

	@Override
	public ResponseReserveDto reserveBook(RequestReserveDto requestReserveDto) {
		public ResponseReserveDto reserveBook(RequestReserveDto requestReserveDto, Integer bookId) {
			if (requestReserveDto == null) {
				throw new CommonException(ExceptionConstants.RESERVE_BOOK_FAILED);
			}

			BookIssuedHistory bookIssuedHistory = new BookIssuedHistory();
			BeanUtils.copyProperties(requestReserveDto, bookIssuedHistory);
			ResponseReserveDto responseReserveDto = new ResponseReserveDto();

			if (requestReserveDto.getStatus().equalsIgnoreCase("available")) {

				bookIssuedHistory.setBookId(bookId);
				bookIssuedHistory.setStatus(BOOKED);
				bookIssuedHistory.setIssuedDate(LocalDate.now());
				bookIssuedHistory.setDueDate(LocalDate.now().plusDays(7));
				responseReserveDto.setMessage("Book issued Successfully");
			} else {

				BookIssuedHistory responseBookIssuedHistoryForPreBook = bookIssuedHistoryRepository
						.findByBookIdAndStatus(bookId, BOOKED);
				bookIssuedHistory.setBookId(bookId);
				bookIssuedHistory.setIssuedDate(responseBookIssuedHistoryForPreBook.getDueDate().plusDays(2));
				bookIssuedHistory.setDueDate(responseBookIssuedHistoryForPreBook.getDueDate().plusDays(7));
				bookIssuedHistory.setStatus("reserved");
				responseReserveDto.setMessage("Book reserved Successfully");
			}
			BookIssuedHistory responseBookIssuedHistory = bookIssuedHistoryRepository.save(bookIssuedHistory);
			if (responseBookIssuedHistory.getBookIssuedId() == null) {
				throw new CommonException(ExceptionConstants.RESERVE_BOOK_FAILED);
			}

			responseReserveDto.setStatusCode(200);
			responseReserveDto.setBookIssuedId(responseBookIssuedHistory.getBookIssuedId());

			Book responseBook = bookRepository.findByBookId(bookId);

			if (responseBook.getStatus().equalsIgnoreCase("available")) {
				responseBook.setStatus(BOOKED);

			} else {
				responseBook.setStatus("reserved");

			}
			bookRepository.save(responseBook);
			return responseReserveDto;
		}
		return null;
	}

}
