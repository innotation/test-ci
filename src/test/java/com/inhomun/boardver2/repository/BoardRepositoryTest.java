package com.inhomun.boardver2.repository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.inhomun.boardver2.dto.BoardDTO;
import com.inhomun.boardver2.dto.BoardFileDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.Arrays;
import java.util.List;

public class BoardRepositoryTest {

    @Mock
    private SqlSessionTemplate sql;

    @InjectMocks
    private BoardRepository boardRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        BoardDTO boardDTO = new BoardDTO();
        // 필요한 속성을 설정합니다.

        boardRepository.save(boardDTO);
        verify(sql, times(1)).insert("Board.save", boardDTO);
    }

    @Test
    public void testUpdateHits() {
        Long id = 1L;

        boardRepository.updateHits(id);
        verify(sql, times(1)).update("Board.updateHits", id);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        BoardDTO expectedBoardDTO = new BoardDTO();

        when(sql.selectOne("Board.findById", id)).thenReturn(expectedBoardDTO);

        BoardDTO result = boardRepository.findById(id);
        assertEquals(expectedBoardDTO, result);
        verify(sql, times(1)).selectOne("Board.findById", id);
    }

    @Test
    public void testUpdate() {
        BoardDTO boardDTO = new BoardDTO();
        // 필요한 속성을 설정합니다.

        boardRepository.update(boardDTO);
        verify(sql, times(1)).update("Board.update", boardDTO);
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        boardRepository.delete(id);
        verify(sql, times(1)).delete("Board.delete", id);
    }

    @Test
    public void testSaveFile() {
        BoardFileDTO boardFileDTO = new BoardFileDTO();
        // 필요한 속성을 설정합니다.

        boardRepository.saveFile(boardFileDTO);
        verify(sql, times(1)).insert("Board.saveFile", boardFileDTO);
    }
}
