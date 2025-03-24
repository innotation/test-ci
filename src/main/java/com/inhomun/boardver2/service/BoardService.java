package com.inhomun.boardver2.service;

import com.inhomun.boardver2.dto.BoardDTO;
import com.inhomun.boardver2.dto.BoardFileDTO;
import com.inhomun.boardver2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardFileDTO> findFile(Long id) {
        return boardRepository.findFile(id);
    }
    public void save(BoardDTO boardDTO) throws IOException {
        if (boardDTO.getBoardFile().get(0).isEmpty()) {
            // 파일이 없는 경우
            boardDTO.setFileAttached(0);
            boardRepository.save(boardDTO);
        }
        else {

            boardDTO.setFileAttached(1);

            BoardDTO saveBoard = boardRepository.save(boardDTO);

            for (MultipartFile boardFile : boardDTO.getBoardFile()) {

                String originalFileName = boardFile.getOriginalFilename();
                System.out.println("originalFileName = " + originalFileName);
                System.out.println(System.currentTimeMillis());
                String storedFileName = System.currentTimeMillis() + "-" + originalFileName;
                System.out.println("storedFileName = " + storedFileName);
                BoardFileDTO boardFileDTO = new BoardFileDTO();
                boardFileDTO.setOriginalFileName(originalFileName);
                boardFileDTO.setStoredFileName(storedFileName);
                boardFileDTO.setBoardId(saveBoard.getId());

                String savePath = "/Users/muninho/development/intellij_community/spring_upload_files/" + storedFileName;
                boardFile.transferTo(new File(savePath));

                boardRepository.saveFile(boardFileDTO);
            }
        }
    }

    public List<BoardDTO> findAll() {
        return boardRepository.findAll();
    }

    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }

    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }
}
