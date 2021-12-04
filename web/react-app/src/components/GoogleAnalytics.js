import React from 'react';
import {Helmet} from "react-helmet";

const GoogleAnalytics = () => {
  const gaId = 'G-JZ7VP9JZTM';

  return (
    <Helmet>
      <script async src={`https://www.googletagmanager.com/gtag/js?id=${gaId}`} />
      <script>
        {`
        window.dataLayer = window.dataLayer || [];
          
          function gtag(){
            dataLayer.push(arguments);
          }
          
          gtag('js', new Date());
          gtag('config', '${gaId}');
        `}
      </script>
    </Helmet>
  )
}

export default GoogleAnalytics;
