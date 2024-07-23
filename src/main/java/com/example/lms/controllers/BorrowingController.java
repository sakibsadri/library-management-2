package com.example.lms.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.example.lms.services.BorrowingService;

@RestController
@RequestMapping("/api/borrowing")
public class BorrowingController {
    
    @Autowired
    private BorrowingService borrowingService;
    
    @PostMapping("/borrow")
    public String borrowBook(@RequestBody BorrowingRequest borrowRequest) {
        return borrowingService.borrowBook(borrowRequest.getBookId(), borrowRequest.getMemberId());
    }
    
    @PostMapping("/return")
    public String returnBook(@RequestBody ReturnRequest returnRequest) {
        return borrowingService.returnBook(returnRequest.getBookId(), returnRequest.getMemberId(), returnRequest.getReturnDate());
    }
    
    public static class BorrowingRequest {
        private Long bookId;
        private Long memberId;
        
        // Getters and Setters
        public Long getBookId() { return bookId; }
        public void setBookId(Long bookId) { this.bookId = bookId; }
        public Long getMemberId() { return memberId; }
        public void setMemberId(Long memberId) { this.memberId = memberId; }
    }
    
    public static class ReturnRequest {
        private Long bookId;
        private Long memberId;
        private LocalDateTime returnDate;
        
        // Getters and Setters
        public Long getBookId() { return bookId; }
        public void setBookId(Long bookId) { this.bookId = bookId; }
        public Long getMemberId() { return memberId; }
        public void setMemberId(Long memberId) { this.memberId = memberId; }
        public LocalDateTime getReturnDate() { return returnDate; }
        public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }
    }
}
