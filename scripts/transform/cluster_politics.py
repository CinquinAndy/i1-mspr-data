from scripts.Extract.data_2022_t1 import extract_2022_t1
from scripts.Extract.data_2017_t1_44_6 import extract_2017_t1

import pandas as pd


def transform():
    df_2022_t1 = extract_2022_t1()
    df_2017_t1 = extract_2017_t1()

    full_df = pd.merge(df_2022_t1, df_2017_t1, on='code_circonscription', how='inner', copy=True)

    # head
    print(full_df.head())
