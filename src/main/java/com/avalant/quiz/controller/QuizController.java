package com.avalant.quiz.controller;

import com.avalant.quiz.service.DataService;
import com.avalant.quiz.dto.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private DataService dataService;

    @GetMapping("/hello-world")
    public String test() {
        return "Hello World";
    }

    // question number 2
    @GetMapping("/max-number")
    public double getMaxValue() {
        List<Double> numbers = dataService.getArrayFromWebService();
        return numbers.stream().max(Double::compare).orElse(Double.NaN);
    }

    // question number 3
    @GetMapping("/fb")
    public List<String> getFB() {
        List<String> result = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            if (i % 15 == 0) {
                result.add("FB");
            } else if (i % 3 == 0) {
                result.add("F");
            } else if (i % 5 == 0) {
                result.add("B");
            } else {
                result.add(String.valueOf(i));
            }
        }

        return result;
    }

    // question number 4
    @GetMapping("/isPalindrome")
    public boolean isPalindrome(@RequestParam(required = true) String text) {
        if (text == null) return false;

        String cleaned = text.replaceAll("[\\W_]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();

        return cleaned.equals(reversed);
    }

    //question number 5
    @GetMapping("/duplicates")
    public Set<String> getDuplicateNames() {
        List<String> list1 = dataService.getFirstNameList();
        List<String> list2 = dataService.getSecondNameList();

        Set<String> duplicates = list1.stream()
                .filter(list2::contains)
                .collect(Collectors.toSet());

        return duplicates;
    }

    //question number 6
    @GetMapping("/fibonacci/{position}")
    public int getFibonacciValue(@PathVariable int position) {
        if (position < 0) {
            throw new IllegalArgumentException("Position must be non-negative.");
        }
        if (position == 0) return 0;
        if (position == 1) return 1;

        int a = 0, b = 1;
        for (int i = 2; i <= position; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    // question number 7
    @GetMapping("/pairs")
    public List<String> findPairs(@RequestParam int target)     {
        List<Integer> nums = dataService.getNumbersFromWebService();
        List<String> result = new ArrayList<>();

        int n = nums.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = nums.get(i);
                int b = nums.get(j);

                if (a + b == target) {
                    result.add(a + " + " + b);
                }
                if (Math.abs(a - b) == target) {
                    if (a > b) result.add(a + " - " + b);
                    else result.add(b + " - " + a);
                }
            }
        }

        return result;
    }

    // question number 8
    @PostMapping("/count")
    public int countWordOccurrences(@RequestBody SearchRequest request) {
        if (request.getText() == null || request.getSearchWord() == null) return 0;

        String[] words = request.getText().toLowerCase().split("\\W+");
        String target = request.getSearchWord().toLowerCase();

        int count = 0;
        for (String word : words) {
            if (word.equals(target)) {
                count++;
            }
        }
        return count;
    }

    //question number 9
    @GetMapping("/days")
    public int getDaysInMonth(@RequestParam String month) {
        try {
            // Parse the month in the format YYYY-MM
            YearMonth yearMonth = YearMonth.parse(month);

            // Get the number of days in the specified month
            return yearMonth.lengthOfMonth();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM format.");
        }
    }

    //question number 10
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty.");
        }

        try {
            // Specify the directory where the file will be saved
            String uploadDir = "uploads/";

            File directory = new File(uploadDir);
            if (!directory.exists() && !directory.mkdirs()) {
                return ResponseEntity.status(500).body("Failed to create upload directory.");
            }

            String fileName = file.getOriginalFilename();
            File destFile = new File(directory, fileName);


            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }
}
