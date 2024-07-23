package com.example.lms.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Book;
import com.example.lms.entity.Borrowing;
import com.example.lms.entity.Member;
import com.example.lms.repositories.BorrowingRepository;

@Service
public class BorrowingService {
    @Autowired
    private BorrowingRepository borrowingRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private MemberService memberService;
    
    public String borrowBook(Long bookId, Long memberId) {
        Optional<Book> bookOpt = bookService.getBook(bookId);
        Optional<Member> memberOpt = memberService.getMember(memberId);
        
        if (!bookOpt.isPresent()) {
            return "Book not available";
        }
        
        if (!memberOpt.isPresent()) {
            return "Member not registered";
        }
        
        Book book = bookOpt.get();
        if (book.getQuantity() <= 0) {
            return "Book not available";
        }
        
        Borrowing borrowing = new Borrowing();
        borrowing.setBook(book);
        borrowing.setMember(memberOpt.get());
        borrowing.setBorrowDate(LocalDateTime.now());
        borrowingRepository.save(borrowing);
        
        book.setQuantity(book.getQuantity() - 1);
        bookService.updateBook(book);
        
        return "Book borrowed successfully";
    }
    
    public String returnBook(Long bookId, Long memberId, LocalDateTime returnDate) {
        Optional<Book> bookOpt = bookService.getBook(bookId);
        Optional<Member> memberOpt = memberService.getMember(memberId);
        
        if (!bookOpt.isPresent() || !memberOpt.isPresent()) {
            return "Book or Member not found";
        }
        
        Book book = bookOpt.get();
        Member member = memberOpt.get();
        
        Optional<Borrowing> borrowingOpt = borrowingRepository.findByBookAndReturnDateIsNull(book);
        
        if (!borrowingOpt.isPresent()) {
            return "Book was not issued";
        }
        
        Borrowing borrowing = borrowingOpt.get();
        borrowing.setReturnDate(returnDate);
        borrowingRepository.save(borrowing);
        
        book.setQuantity(book.getQuantity() + 1);
        bookService.updateBook(book);
        
        return "Book returned successfully";
    }
}