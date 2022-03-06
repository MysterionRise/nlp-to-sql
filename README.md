# Repo dedicated to different approaches of transforming natural language questions to structured ones such as SQL

## [SQLova](https://github.com/naver/sqlova) 

- [Wonseok Hwang](mailto:wonseok.hwang@navercorp.com), [Jinyeong Yim](mailto:jinyeong.yim@navercorp.com), [Seunghyun Park](mailto:seung.park@navercorp.com), and [Minjoon Seo](https://seominjoon.github.io).
- Affiliation: Clova AI Research, NAVER Corp., Seongnam, Korea.
- The updated version of manuscript is available from [arXiv](https://arxiv.org/abs/1902.01069).

### How to run examples

#### Requirements

Tested on `Python 3.8` and `Cuda 11.3`

- Install [minicoda](https://conda.io/miniconda.html) and following dependencies
    - `conda install pytorch torchvision -c pytorch`
    - `conda install -c conda-forge records`
    - `conda install babel`
    - `conda install matplotlib`
    - `conda install defusedxml`
    - `conda install tqdm`
    - `conda install -c conda-forge stanfordcorenlp`
    - `conda install -c anaconda sqlalchemy`
    - `conda install -c ericmjl isort`
    - `conda install -c conda-forge black`

#### Models and data

Download annotated WikiSQL data and the PyTorch-converted pre-trained BERT
parameters [here](https://drive.google.com/file/d/1iJvsf38f16el58H4NPINQ7uzal5-V4v4/view?usp=sharing) and place whole
folder under data folder

#### Run

`python3 train.py` you will be prompted with topic lookup and then question

