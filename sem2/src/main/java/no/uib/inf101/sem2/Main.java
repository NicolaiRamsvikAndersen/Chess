package no.uib.inf101.sem2;

import no.uib.inf101.sem2.controller.ChessController;
import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.ChessModel;
import no.uib.inf101.sem2.view.ChessView;
import no.uib.inf101.sem2.view.DefaultColorTheme;
//import no.uib.inf101.sem2.view.SampleView;

import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    ChessBoard board = new ChessBoard(8, 8);
    ChessModel model = new ChessModel(board);
    DefaultColorTheme theme = new DefaultColorTheme();
    ChessView view = new ChessView(model, theme);
    // SampleView view = new SampleView();

    new ChessController(model, view);

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("INF101");
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }
}
