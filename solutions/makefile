all: main.pdf

%.pdf: %.tex
	pdflatex $<
	bibtex $<||true
	pdflatex $<
	pdflatex $<

clean:
	rm -f *.ps
	rm -f *.log
	rm -f *.aux
	rm -f *.out
	rm -f *.dvi
	rm -f *.bbl
	rm -f *.blg
	rm -f *.bak
	rm -f *.synctex.gz

cleanall: clean
	rm -f *.pdf
