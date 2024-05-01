//package com.example.demo.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")  // すべてのエンドポイントに対してCORSを適用
//                .allowedOrigins("http://localhost:3000")  // 許可するオリジン
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 許可するHTTPメソッド
//                .allowedHeaders("*")  // 許可するヘッダー
//                .allowCredentials(true)  // クレデンシャルを許可するか
//                .maxAge(3600);  // プリフライトリクエストのキャッシュ時間（秒）
//    }
//}
