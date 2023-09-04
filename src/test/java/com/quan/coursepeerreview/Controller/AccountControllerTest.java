package com.quan.coursepeerreview.Controller;

import com.quan.coursepeerreview.Repository.*;
import com.quan.coursepeerreview.Service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountRepos accountRepos;

    @MockBean
    private StudentRepos studentRepos;

    @MockBean
    private CourseRepos courseRepos;

    @MockBean
    private PeerReviewRepos peerReviewRepos;

    @MockBean
    private CourseQuestionsRepos courseQuestionsRepos;

    @MockBean
    private QuestionRepos questionRepos;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllAccounts() throws Exception {
        this.mockMvc.perform(get("/api/v1/account/ROLE_ADMIN"))
                .andExpect(status().isOk());
    }

}
